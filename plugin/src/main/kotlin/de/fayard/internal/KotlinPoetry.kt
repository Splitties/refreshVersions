package de.fayard.internal

import com.squareup.kotlinpoet.CodeBlock
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import com.squareup.kotlinpoet.asClassName
import de.fayard.BuildSrcVersionsExtension
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec


fun kotlinpoet(versions: List<Dependency>, gradleConfig: GradleConfig, extension: BuildSrcVersionsExtension): KotlinPoetry {

    val gradleVersion = constStringProperty(
        PluginConfig.GRADLE_LATEST_VERSION,
        gradleConfig.current.version,
        CodeBlock.of(PluginConfig.gradleKdoc(gradleConfig.running.version))
    )

    val versionsProperties: List<PropertySpec> = versions
        .distinctBy { it.versionName }
        .map(Dependency::generateVersionProperty) + gradleVersion

    val libsProperties: List<PropertySpec> = versions
        .distinctBy { it.escapedName }
        .map { it.generateLibsProperty(extension) }

    val Versions: TypeSpec = TypeSpec.objectBuilder(extension.renameVersions)
        .addKdoc(PluginConfig.KDOC_VERSIONS)
        .addProperties(versionsProperties)
        .build()


    val Libs = TypeSpec.objectBuilder(extension.renameLibs)
        .addKdoc(PluginConfig.KDOC_LIBS)
        .addProperties(libsProperties)
        .build()


    val LibsFile = FileSpec.builder("", extension.renameLibs)
        .indent(extension.indent)
        .addType(Libs)
        .build()

    val VersionsFile = FileSpec.builder("", extension.renameVersions)
        .indent(extension.indent)
        .addType(Versions)
        .apply { addMaybeBuildSrcVersions(versions, extension) }
        .build()

    return KotlinPoetry(Libs = LibsFile, Versions = VersionsFile)

}

// https://github.com/jmfayard/buildSrcVersions/issues/65
fun <T> List<T>.sortedBeautifullyBy(exceptIf: Boolean = false, selection: (T) -> String?) : List<T> =
    this.filterNot { selection(it) == null }
        .sortedBy { selection(it)!! }
        .sortedByDescending { selection(it)!!.length }

fun FileSpec.Builder.addMaybeBuildSrcVersions(versions: List<Dependency>, extension: BuildSrcVersionsExtension) {
    versions.firstOrNull {
        it.name in listOf("de.fayard.buildSrcVersions.gradle.plugin", "buildSrcVersions-plugin")
    }?.let { buildSrcVersionsDependency ->
        val pluginAccessorForBuildSrcVersions = pluginProperty(
            id = "de.fayard.buildSrcVersions",
            property = "buildSrcVersions",
            dependency = buildSrcVersionsDependency,
            kdoc = CodeBlock.of(PluginConfig.issue47UpdatePlugin),
            extension = extension
        )
        addProperty(pluginAccessorForBuildSrcVersions)
    }
}

fun Dependency.generateVersionProperty(): PropertySpec {
    return constStringProperty(
        name = versionName,
        initializer = CodeBlock.of("%S%L", version, versionInformation())
    )
}

fun Dependency.versionInformation(): String {
    val newerVersion = newerVersion()
    val comment = when {
        version == "none" -> "// No version. See buildSrcVersions#23"
        newerVersion == null -> ""
        else -> """ // available: "$newerVersion""""
    }
    val addNewLine = comment.length + versionName.length + version.length > 70

    return if (addNewLine) "\n$comment" else comment
}

fun Dependency.newerVersion(): String?  =
    when {
        available == null -> null
        available.release.isNullOrBlank().not() -> available.release
        available.milestone.isNullOrBlank().not() -> available.milestone
        available.integration.isNullOrBlank().not() -> available.integration
        else -> null
    }

fun Dependency.generateLibsProperty(extension: BuildSrcVersionsExtension): PropertySpec {
    val libValue = when(version) {
        "none" -> CodeBlock.of("%S", "$group:$name")
        else -> CodeBlock.of("%S + ${extension.renameVersions}.%L", "$group:$name:", versionName)
    }

    val libComment = when {
        projectUrl == null -> null
         else -> CodeBlock.of("%L", this.projectUrl)
    }

    return constStringProperty(
        name = escapedName,
        initializer = libValue,
        kdoc = libComment
    )

}


fun parseGraph(
    graph: DependencyGraph,
    useFdqnByDefault: List<String>
): List<Dependency> {

    val dependencies: List<Dependency> = graph.current + graph.exceeded + graph.outdated + graph.unresolved

    val map = mutableMapOf<String, Dependency>()
    for (d: Dependency in dependencies) {
        val key = PluginConfig.escapeVersionsKt(d.name)
        val fdqnName = d.fqdnName()


        if (key in useFdqnByDefault) {
            d.escapedName = fdqnName
        } else if (map.containsKey(key)) {
            d.escapedName = fdqnName

            // also use FDQN for the dependency that conflicts with this one
            val other = map[key]!!
            other.escapedName = other.fqdnName()
        } else {
            map[key] = d
            d.escapedName = key
        }
    }
    return dependencies
        .findCommonVersions()
        .sortedBeautifullyBy(exceptIf = OutputFile.VERSIONS.existed) { it.versionName }
}

fun Dependency.fqdnName(): String = PluginConfig.escapeVersionsKt("${group}_${name}")


fun List<Dependency>.orderDependencies(): List<Dependency> {
    return this.sortedBy { it.gradleNotation() }
}

fun List<Dependency>.findCommonVersions(): List<Dependency> {
    val map = groupBy { d -> d.group }
    for (deps in map.values) {
        val groupTogether = deps.size > 1  && deps.map { it.version }.distinct().size == 1

        for (d in deps) {
            d.versionName = if (groupTogether) PluginConfig.escapeVersionsKt(d.group) else d.escapedName
        }
    }
    return this
}

fun constStringProperty(name: String, initializer: CodeBlock, kdoc: CodeBlock? = null) =
    PropertySpec.builder(name, String::class)
        .addModifiers(KModifier.CONST)
        .initializer(initializer)
        .apply {
            if (kdoc != null) addKdoc(kdoc)
        }.build()

fun pluginProperty(
    id: String,
    property: String,
    dependency: Dependency,
    kdoc: CodeBlock? = null,
    extension: BuildSrcVersionsExtension
): PropertySpec {
    val type = PluginDependencySpec::class.asClassName()
    return PropertySpec.builder(property, type)
        .apply { if (kdoc!= null) addKdoc(kdoc) }
        .receiver(PluginDependenciesSpec::class.asClassName())
        .getter(
            FunSpec.getterBuilder()
            .addModifiers(KModifier.INLINE)
            .addStatement("return id(%S).version(${extension.renameVersions}.%L)", id, dependency.versionName)
            .build()
        )
        .build()
}

fun constStringProperty(name: String, initializer: String, kdoc: CodeBlock? = null) =
    constStringProperty(name, CodeBlock.of("%S", initializer), kdoc)


