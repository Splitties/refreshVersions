package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.DependencySelection
import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.extensions.gradle.isBuildSrc
import de.fayard.refreshVersions.core.extensions.gradle.isRootProject
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel.Section.VersionEntry
import de.fayard.refreshVersions.core.internal.versions.readFromFile
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.gradle.api.Project
import org.gradle.api.initialization.Settings
import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

@InternalRefreshVersionsApi
object RefreshVersionsConfigHolder {

    internal val resettableDelegates = ResettableDelegates()

    internal val isUsingVersionRejection: Boolean get() = versionRejectionFilter != null

    var versionRejectionFilter: (DependencySelection.() -> Boolean)? by resettableDelegates.NullableDelegate()

    private val versionKeyReaderDelegate = resettableDelegates.LateInit<ArtifactVersionKeyReader>()

    var versionKeyReader: ArtifactVersionKeyReader by versionKeyReaderDelegate
        private set

    var versionsPropertiesFile: File by resettableDelegates.LateInit()
        private set

    val buildSrc: Project? get() = buildSrcSettings?.gradle?.rootProject

    /**
     * Not initialized when the IDE syncs the buildSrc of the project alone (without the host project)
     */
    internal var settings: Settings by resettableDelegates.LateInit()
        private set

    internal var lastlyReadVersionsMap: Map<String, String> by resettableDelegates.MutableLazy {
        readVersionsMap()
    }

    internal fun readVersionsPropertiesModel() = VersionsPropertiesModel.readFromFile(versionsPropertiesFile)

    fun readVersionsMap(): Map<String, String> {
        return readVersionsMap(readVersionsPropertiesModel())
    }

    internal fun readVersionsMap(model: VersionsPropertiesModel): Map<String, String> {
        return model.sections.filterIsInstance<VersionEntry>().associate { it.key to it.currentVersion }.also {
            lastlyReadVersionsMap = it
        }
    }

    fun allProjects(project: Project): Sequence<Project> {
        require(project.isRootProject)
        val buildSrcProjects = buildSrc?.allprojects?.asSequence() ?: emptySequence()
        return buildSrcProjects + project.allprojects.asSequence()
    }

    internal val resultMode: VersionCandidatesResultMode = VersionCandidatesResultMode(
        filterMode = VersionCandidatesResultMode.FilterMode.AllIntermediateVersions,
        sortingMode = VersionCandidatesResultMode.SortingMode.ByVersion
    )

    internal inline fun <R> withHttpClient(
        noinline logHttpCall: ((String) -> Unit)? = { message -> println(message) },
        block: (httpClient: OkHttpClient) -> R
    ): R {
        val client = OkHttpClient.Builder().let { builder ->
            if (logHttpCall == null) builder
            else builder.addInterceptor( //TODO: Allow disabling/configuring logging.
                HttpLoggingInterceptor(logHttpCall).setLevel(HttpLoggingInterceptor.Level.BASIC)
            )
        }.build()
        try {
            return block(client)
        } finally {
            client.dispatcher.executorService.shutdown()
        }
    }

    internal fun initialize(
        settings: Settings,
        artifactVersionKeyRules: List<String>,
        getRemovedDependenciesVersionsKeys: () -> Map<ModuleId.Maven, String>,
        versionsPropertiesFile: File,
        versionRejectionFilter: (DependencySelection.() -> Boolean)?
    ) {
        require(settings.isBuildSrc.not())
        resettableDelegates.reset()
        this.settings = settings

        this.versionsPropertiesFile = versionsPropertiesFile.also {
            it.createNewFile() // Creates the file if it doesn't exist yet
        }
        this.versionRejectionFilter = versionRejectionFilter
        this.artifactVersionKeyRules = artifactVersionKeyRules
        versionKeyReader = ArtifactVersionKeyReader.fromRules(
            filesContent = artifactVersionKeyRules,
            getRemovedDependenciesVersionsKeys = getRemovedDependenciesVersionsKeys
        )
    }

    internal fun initializeBuildSrc(
        settings: Settings,
        getRemovedDependenciesVersionsKeys: () -> Map<ModuleId.Maven, String>
    ) {
        require(settings.isBuildSrc)
        buildSrcSettings = settings

        // The buildSrc will be built a second time as a standalone project by IntelliJ or
        // Android Studio after running initially properly after host project settings evaluation.
        // To work around this, we persist the configuration and attempt restoring it if needed,
        // to not fail the second build (since it'd display errors, and
        // prevent from seeing resolved dependencies in buildSrc sources in the IDE).
        //
        // Should the IDE really build the buildSrc as a standalone mode when not explicitly
        // requested?
        // Might be worth opening an issue on https://youtrack.jetbrains.com to find out.
        if (versionKeyReaderDelegate.isInitialized) {
            persistInitData(settings)
        } else {
            runCatching {
                restorePersistedInitData(settings, getRemovedDependenciesVersionsKeys)
            }.onFailure { e ->
                throw IllegalStateException(
                    "You also need to bootstrap refreshVersions in the " +
                        "settings.gradle[.kts] file of the root project",
                    e
                )
            }
        }
    }

    private var artifactVersionKeyRules: List<String> by resettableDelegates.LateInit()

    internal var buildSrcSettings: Settings? by resettableDelegates.NullableDelegate()
        private set


    private fun persistInitData(settings: Settings) {
        versionsPropertiesFile // Check it is initialized by accessing it.
        artifactVersionKeyRules // Check it is initialized by accessing it.
        versionKeyReader // Check it is initialized by accessing it.

        settings.artifactVersionKeyRulesFile.let { file ->
            if (file.exists().not()) {
                file.parentFile.mkdirs()
                file.createNewFile()
            }
            ObjectOutputStream(file.outputStream()).use {
                it.writeObject(artifactVersionKeyRules.toTypedArray())
            }
        }
        settings.versionsPropertiesFileFile.let { file ->
            if (file.exists().not()) {
                file.parentFile.mkdirs()
                file.createNewFile()
            }
            ObjectOutputStream(file.outputStream()).use {
                it.writeObject(versionsPropertiesFile)
            }
        }
    }

    private fun restorePersistedInitData(
        settings: Settings,
        getRemovedDependenciesVersionsKeys: () -> Map<ModuleId.Maven, String> = { emptyMap() }
    ) {
        versionsPropertiesFile = settings.versionsPropertiesFileFile.let { file ->
            ObjectInputStream(file.inputStream()).use { it.readObject() as File }
        }
        artifactVersionKeyRules = settings.artifactVersionKeyRulesFile.let { file ->
            ObjectInputStream(file.inputStream()).use {
                @Suppress("unchecked_cast")
                (it.readObject() as Array<String>).asList()
            }
        }
        versionKeyReader = ArtifactVersionKeyReader.fromRules(
            filesContent = artifactVersionKeyRules,
            getRemovedDependenciesVersionsKeys = getRemovedDependenciesVersionsKeys
        )
    }

    private val Settings.artifactVersionKeyRulesFile: File
        get() = rootDir.resolve("build").resolve("refreshVersions_artifactVersionKeyRules.bin")

    private val Settings.versionsPropertiesFileFile: File
        get() = rootDir.resolve("build").resolve("refreshVersions_versionsPropertiesFilePath.bin")
}
