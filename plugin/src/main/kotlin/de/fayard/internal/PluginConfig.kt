package de.fayard.internal

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import de.fayard.internal.VersionMode.GROUP
import de.fayard.internal.VersionMode.GROUP_MODULE
import de.fayard.internal.VersionMode.MODULE
import okio.buffer
import okio.source
import org.gradle.util.GradleVersion
import java.io.File

@Suppress("unused")
object PluginConfig {
    const val PLUGIN_ID = "de.fayard.refreshVersions"
    const val PLUGIN_VERSION = "0.8.2" // plugin.de.fayard.refreshVersions
    const val GRADLE_VERSIONS_PLUGIN_ID = "com.github.ben-manes.versions"
    const val GRADLE_VERSIONS_PLUGIN_VERSION = "0.25.0" // Sync with plugin/build.gradle.kts
    const val DEPENDENCY_UPDATES = "dependencyUpdates"
    const val REFRESH_VERSIONS_UPDATES_PATH = "refreshVersionsUpdates" // TODO: think about a better name
    const val USE_EXPERIMENTAL_UPDATER = "refreshVersions.useExperimentalUpdater" // TODO: think about a better name
    const val DEPENDENCY_UPDATES_PATH = ":$DEPENDENCY_UPDATES"
    const val REFRESH_VERSIONS = "refreshVersions"
    const val EXTENSION_NAME = REFRESH_VERSIONS
    const val DEFAULT_PROPERTIES_FILE = "versions.properties"
    const val AVAILABLE_DEPENDENCIES_FILE = "build/dependencyUpdates/refreshVersions.txt"

    /** There is no standard on how to name stable and unstable versions
     * This version is a good starting point but you can define you rown
     */
    @JvmStatic
    fun isNonStable(version: String): Boolean {
        val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
        val regex = "^[0-9,.v-]+$".toRegex()
        val isStable = stableKeyword || regex.matches(version)
        return isStable.not()
    }

    /**
     * Naming convention:
     * Given a dependency like
     *      "org.jetbrains.kotlin:kotlin-stdlib"
     * the resolutionStrategy will pick the first of those
     *      version.org.jetbrains.kotlin.kotlin.stdlib=1.3.50
     *      version.org.jetbrains.kotlin=1.3.50
     *      version.kotlin.stdlib=1.3.50
     * Gradle properties can be set either in "gradle.properties" or from the command-line with
     *      $ ./gradlew -Pversion.kotlin.stdlib=1.3.50
     *  **/
    fun considerGradleProperties(group: String, module: String): List<String> = listOfNotNull(
        "version.$group..$module",
        Dependency.virtualGroup(Dependency(group = group, name = module)),
        "version.$group",
        "version.$module"
    )

    // TODO: replace ALIGN_VERSION_GROUPS by DEFAULT_MAPPING
    val ALIGN_VERSION_GROUPS: MutableList<String>
        get() = DEFAULT_MAPPING.values.toMutableList()

    val DEFAULT_MAPPING: Map<String, String> = mapOf(
        "org.jetbrains.kotlinx..kotlinx-coroutines" to "org.jetbrains.kotlinx.kotlinx-coroutines",
        "org.jetbrains.kotlinx..kotlinx-serialization" to "org.jetbrains.kotlinx.kotlinx-serialization",
        "com.louiscad.splitties..splitties" to "com.louiscad.splitties:splitties",
        "com.squareup.retrofit2" to "com.squareup.retrofit2"
        /**
         * TODO: improve DEFAULT_MAPPING according to
         * https://github.com/LouisCAD/Splitties/blob/9f4a85e7ecc612d290fd6e4615b3472c05aece51/build.gradle.kts#L151-L167
         * for example:
        "retrofit2" to "com.squareup.retrofit2",
        "kotlin" to "org.jetbrains.kotlin:kotlin",
        "moshi" to "com.squareup.moshi"
         **/
    )

    @JvmStatic
    fun versionPropertyFor(d: Dependency): String = when (d.mode) {
        MODULE -> d.name
        GROUP -> d.groupOrVirtualGroup()
        GROUP_MODULE -> "${d.group}..${d.name}"
    }

    fun versionKtFor(d: Dependency): String = escapeVersionsKt(
        when (d.mode) {
            MODULE -> d.name
            GROUP -> d.groupOrVirtualGroup()
            GROUP_MODULE -> "${d.group}:${d.name}"
        }
    )

    fun escapeVersionsKt(name: String): String {
        val escapedChars = listOf('-', '.', ':')
        return buildString {
            for (c in name) {
                append(if (c in escapedChars) '_' else c.toLowerCase())
            }
        }
    }


    const val DEFAULT_LIBS = "Libs"
    const val DEFAULT_VERSIONS = "Versions"
    const val SPACES4 = "    "
    const val SPACES2 = "  "
    const val SPACES0 = ""
    const val TAB = "\t"
    const val DEFAULT_INDENT = SPACES4
    const val BENMANES_REPORT_PATH = "build/dependencyUpdates/report.json"

    /** Documentation **/
    fun issue(number: Int): String = "$buildSrcVersionsUrl/issues/$number"

    val buildSrcVersionsUrl = "https://github.com/jmfayard/buildSrcVersions"
    val issue47UpdatePlugin = "See issue #47: how to update buildSrcVersions itself ${issue(47)}"
    val issue53PluginConfiguration = issue(53)
    val issue54VersionOnlyMode = issue(54)
    val issue19UpdateGradle = issue(19)
    val issue77RefreshVersionsGradleProperties = issue(77)


    /**
     * We don't want to use meaningless generic libs like Libs.core
     *
     * Found many inspiration for bad libs here https://developer.android.com/jetpack/androidx/migrate
     * **/
    val MEANING_LESS_NAMES: List<String> = listOf(
        "common", "core", "testing", "runtime", "extensions",
        "compiler", "migration", "db", "rules", "runner", "monitor", "loader",
        "media", "print", "io", "collection", "gradle", "android"
    )

    val INITIAL_GITIGNORE = """
        |.gradle/
        |build/
        """.trimMargin()

    fun gradleKdoc(currentVersion: String): String = """
        |Current version: "$currentVersion"
        |See issue 19: How to update Gradle itself?
        |$issue19UpdateGradle
    """.trimMargin()


    val moshi: Moshi = Moshi.Builder().build()

    inline fun <reified T : Any> moshiAdapter(clazz: Class<T> = T::class.java): Lazy<JsonAdapter<T>> = lazy { moshi.adapter(clazz) }

    val dependencyGraphAdapter: JsonAdapter<DependencyGraph> by moshiAdapter()

    internal val extensionAdapter: JsonAdapter<RefreshVersionsExtensionImpl> by moshiAdapter()

    internal val dependencyAdapter: JsonAdapter<Dependency> by moshiAdapter()

    fun readGraphFromJsonFile(jsonInput: File): DependencyGraph {
        return dependencyGraphAdapter.fromJson(jsonInput.source().buffer())!!
    }

    val VERSIONS_ONLY_START = "<buildSrcVersions>"
    val VERSIONS_ONLY_END = "</buildSrcVersions>"
    val VERSIONS_ONLY_INTRO: List<String> = listOf(
        VERSIONS_ONLY_START,
        "Generated by ./gradle buildSrcVersions",
        "See $issue54VersionOnlyMode"
    )

    val REFRESH_VERSIONS_START: List<String> = listOf(
        "# Dependencies and Plugin versions with their available updates",
        "# Generated by $ ./gradlew refreshVersions",
        "# You can edit the rest of the file, it will be kept intact",
        "# See $issue77RefreshVersionsGradleProperties"
    )
    var isAndroidProject: Boolean = false
    val MODULES
        get() = listOfNotNull(
            "module.kotlin=1.3.50",
            if (isAndroidProject) "module.android=3.5.0" else null
        )

    val REFRESH_VERSIONS_END: List<String> = listOf()

    val OLD_LINES: List<String> = listOf(
        "# Plugin versions"
    )

    val ALL_GRADLE_PROPERTIES_LINES = REFRESH_VERSIONS_START + REFRESH_VERSIONS_END + OLD_LINES

    const val GRADLE_LATEST_VERSION = "gradleLatestVersion"

    fun supportsTaskAvoidance(): Boolean =
        GradleVersion.current() >= GradleVersion.version("5.0")

    fun spaces(nbSpaces: Int): String =
        StringBuilder().run {
            repeat(Math.max(0, nbSpaces)) {
                append(' ')
            }
            toString()
        }


    val gradleVersionsPlugin: Dependency = Dependency(
        group = "com.github.ben-manes",
        name = "$GRADLE_VERSIONS_PLUGIN_ID.gradle.plugin",
        version = GRADLE_VERSIONS_PLUGIN_VERSION,
        mode = MODULE,
        available = null
    )

    val gradleRefreshVersions: Dependency = Dependency(
        group = "de.fayard",
        name = "$PLUGIN_ID.gradle.plugin",
        version = PLUGIN_VERSION,
        mode = MODULE,
        available = null
    )

    fun gradleLatestVersion(graph: DependencyGraph): Dependency = Dependency(
        group = "org.gradle",
        name = GRADLE_LATEST_VERSION,
        mode = MODULE,
        version = graph.gradle.running.version,
        available = when {
            graph.gradle.running == graph.gradle.current -> null
            else -> AvailableDependency(release = graph.gradle.current.version)
        }
    )

    fun computeUseFqdnFor(
        dependencies: List<Dependency>,
        configured: List<String>,
        byDefault: List<String> = MEANING_LESS_NAMES
    ) : List<String> {
        val groups = (configured + byDefault).filter { it.contains(".") }.distinct()
        val depsFromGroups = dependencies.filter { it.group in groups }.map { it.module }
        val ambiguities = dependencies.groupBy { it.module }.filter { it.value.size > 1 }.map { it.key }
        return (configured + byDefault + ambiguities + depsFromGroups - groups).distinct().sorted()
    }

    lateinit var configureGradleVersions: (DependencyUpdatesTask.() -> Unit) -> Unit

}
