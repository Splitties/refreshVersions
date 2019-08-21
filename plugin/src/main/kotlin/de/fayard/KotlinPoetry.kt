package de.fayard

import com.squareup.kotlinpoet.*
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec


fun kotlinpoet(versions: List<Dependency>, gradleConfig: GradleConfig, extension: BuildSrcVersionsExtension): KotlinPoetry {

    val versionsProperties: List<PropertySpec> = versions
        .distinctBy { it.versionName }
        .map(Dependency::generateVersionProperty)

    val libsProperties: List<PropertySpec> = versions
        .distinctBy { it.escapedName }
        .map { it.generateLibsProperty(extension) }

    val gradleProperties: List<PropertySpec> = listOf(
        constStringProperty("gradleLatestVersion", gradleConfig.current.version, CodeBlock.of(PluginConfig.GRADLE_KDOC)),
        constStringProperty("gradleCurrentVersion", gradleConfig.running.version)
    )

    val Versions: TypeSpec = TypeSpec.objectBuilder(extension.renameVersions)
        .addKdoc(PluginConfig.KDOC_VERSIONS)
        .addProperties(versionsProperties + gradleProperties)
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

fun FileSpec.Builder.addMaybeBuildSrcVersions(versions: List<Dependency>, extension: BuildSrcVersionsExtension) {
    versions.firstOrNull {
        it.name in listOf("de.fayard.buildSrcVersions.gradle.plugin", "buildSrcVersions-plugin")
    }?.let { buildSrcVersionsDependency ->
        val pluginAccessorForBuildSrcVersions = pluginProperty(
            id = "de.fayard.buildSrcVersions",
            property = "buildSrcVersions",
            dependency = buildSrcVersionsDependency,
            kdoc = PluginConfig.issue47,
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
    val comment = when {
        version == "none" -> "// No version. See buildSrcVersions#23"
        available == null -> ""
        else -> " ${available.displayComment()}"
    }
    return if (comment.length + versionName.length + version.length > 70) {
            '\n' + comment
        } else {
            comment
        }
}

fun AvailableDependency.displayComment(): String {
    val newerVersion: String? = when {
        release.isNullOrBlank().not() -> release
        milestone.isNullOrBlank().not() -> milestone
        integration.isNullOrBlank().not() -> integration
        else -> null
    }
    return  if (newerVersion == null) "// $this" else """// available: "$newerVersion""""
}



fun Dependency.generateLibsProperty(extension: BuildSrcVersionsExtension): PropertySpec {
    // https://github.com/jmfayard/buildSrcVersions/issues/23
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
        val key = escapeName(d.name)
        val fdqnName = d.fdqnName()


        if (key in useFdqnByDefault) {
            d.escapedName = fdqnName
        } else if (map.containsKey(key)) {
            d.escapedName = fdqnName

            // also use FDQN for the dependency that conflicts with this one
            val other = map[key]!!
            other.escapedName = other.fdqnName()
        } else {
            map[key] = d
            d.escapedName = key
        }
    }
    return dependencies.orderDependencies().findCommonVersions()
}

fun Dependency.fdqnName(): String = escapeName("${group}_${name}")


fun List<Dependency>.orderDependencies(): List<Dependency> {
    return this.sortedBy { it.gradleNotation() }
}

fun List<Dependency>.findCommonVersions(): List<Dependency> {
    val map = groupBy { d -> d.group }
    for (deps in map.values) {
        val groupTogether = deps.size > 1  && deps.map { it.version }.distinct().size == 1

        for (d in deps) {
            d.versionName = if (groupTogether) escapeName(d.group) else d.escapedName
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


fun escapeName(name: String): String {
    val escapedChars = listOf('-', '.', ':')
    return buildString {
        for (c in name) {
            append(if (c in escapedChars) '_' else c.toLowerCase())
        }
    }
}
