package de.fayard.refreshVersions.internal

import com.squareup.kotlinpoet.*


internal data class Dependency(
    val group: String = "",
    val module: String = "",
    val version: String = ""
) {
    val name: String get() = module
    fun groupModuleVersion() = "$group:$module:$version"
    fun groupModuleUnderscore() = "$group:$module:_"
    fun groupModule() = "$group:$module"
    fun versionName(mode: VersionMode): String = PluginConfig.escapeLibsKt(
        when (mode) {
            VersionMode.MODULE -> module
            VersionMode.GROUP -> group
            VersionMode.GROUP_MODULE -> "${group}_$module"
        }
    )

    override fun toString() = groupModuleVersion()
}


internal class Deps(
    val dependencies: List<Dependency>,
    val modes: Map<Dependency, VersionMode>,
    val names: Map<Dependency, String>
)


internal enum class VersionMode {
    GROUP, GROUP_MODULE, MODULE
}


internal fun kotlinpoet(
    deps: Deps
): FileSpec {
    val dependencies: List<Dependency> = deps.dependencies
    val indent = "    "

    val libsProperties: List<PropertySpec> = dependencies
        .distinctBy { it.groupModule() }
        .map { d ->
            val libValue = when {
                d.version == "none" -> CodeBlock.of("%S", d.groupModule())
                else -> CodeBlock.of("%S", d.groupModuleUnderscore())
            }
            constStringProperty(
                name = deps.names[d]!!,
                initializer = libValue,
                kdoc = null
            )
        }

    val Libs = TypeSpec.objectBuilder("Libs")
        .addKdoc(PluginConfig.KDOC_LIBS)
        .addProperties(libsProperties)
        .build()


    val LibsFile = FileSpec.builder("", "Libs")
        .indent(indent)
        .addType(Libs)
        .build()

    return LibsFile

}

internal fun List<Dependency>.checkModeAndNames(useFdqnByDefault: List<String>): Deps {
    val dependencies = this

    val modes: MutableMap<Dependency, VersionMode> = dependencies.associate { d: Dependency ->
        val mode = when {
            d.module in useFdqnByDefault -> VersionMode.GROUP_MODULE
            PluginConfig.escapeLibsKt(d.module) in useFdqnByDefault -> VersionMode.GROUP_MODULE
            else -> VersionMode.MODULE
        }
        Pair(d, mode)
    }.toMutableMap()

    val versionNames = dependencies.associate { d: Dependency ->
        Pair(d, d.versionName(modes[d]!!))
    }
    val sortedDependencies = dependencies.sortedBy { d: Dependency -> d.groupModule() }
    return Deps(sortedDependencies, modes, versionNames)
}


internal fun constStringProperty(
    name: String,
    initializer: CodeBlock,
    kdoc: CodeBlock? = null
): PropertySpec = PropertySpec.builder(name, String::class)
    .addModifiers(KModifier.CONST)
    .initializer(initializer)
    .apply {
        if (kdoc != null) addKdoc(kdoc)
    }.build()
