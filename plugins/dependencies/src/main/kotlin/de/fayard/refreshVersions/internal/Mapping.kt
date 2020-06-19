package de.fayard.refreshVersions.internal

import AndroidX
import Firebase
import Google
import JakeWharton
import Kotlin
import KotlinX
import Ktor
import Splitties
import Square
import Testing
import org.gradle.api.artifacts.ModuleIdentifier
import kotlin.reflect.KProperty1
import kotlin.reflect.KVisibility
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaField
import kotlin.reflect.typeOf

internal data class DependencyMapping(
    val group: String,
    val artifact: String,
    val constantName: String
) {
    companion object {
        fun fromLine(line: String): DependencyMapping? {
            if (line.isEmpty()) return null
            val (key, constantName) = line.split("=").takeIf { it.size == 2 } ?: return null
            val (group, artifact) = key.split("..").takeIf { it.size == 2 } ?: return null
            return DependencyMapping(group, artifact, constantName)
        }
    }

    override fun toString(): String = string

    private val string by lazy(LazyThreadSafetyMode.NONE) { "$group..$artifact=$constantName" }
}

internal fun getArtifactNameToConstantMapping(excludeBomDependencies: Boolean = false): List<DependencyMapping> {
    return listOf(
        AndroidX,
        Google,
        JakeWharton,
        Firebase,
        Kotlin,
        KotlinX,
        Splitties,
        Square,
        Ktor,
        Testing
    ).flatMap { objectInstance ->
        getArtifactNameToConstantMappingFromObject(
            objectInstance,
            excludeBomDependencies = excludeBomDependencies
        )
    }.sortedBy { it.toString() }
}

internal fun getArtifactsFromDependenciesObject(objectInstance: Any): List<ModuleIdentifier> {
    return getArtifactNameToConstantMappingFromObject(
        objectInstance,
        objectInstance::class.simpleName!!
    ).map {
        object : ModuleIdentifier {
            override fun getGroup(): String = it.group
            override fun getName(): String = it.artifact
        }
    }
}

private fun getArtifactNameToConstantMappingFromObject(
    objectInstance: Any,
    prefix: String = with(objectInstance::class) {
        if (isCompanion) qualifiedName!!.removeSuffix(".Companion") else qualifiedName!!
    },
    excludeBomDependencies: Boolean = false
): List<DependencyMapping> {
    val objectClass = objectInstance::class
    return objectClass.memberProperties.filter { kProperty ->
        @UseExperimental(ExperimentalStdlibApi::class)
        kProperty.visibility == KVisibility.PUBLIC && kProperty.returnType != typeOf<String>()
    }.flatMap { kProperty ->
        @Suppress("unchecked_cast")
        val nestedObjectInstance = (kProperty as KProperty1<Any?, Any>).get(objectInstance)
        getArtifactNameToConstantMappingFromObject(
            objectInstance = nestedObjectInstance,
            prefix = "$prefix.${kProperty.name}",
            excludeBomDependencies = excludeBomDependencies
        )
    } + objectClass.memberProperties.asSequence().filter { kProperty ->
        @UseExperimental(ExperimentalStdlibApi::class)
        kProperty.visibility == KVisibility.PUBLIC && kProperty.returnType == typeOf<String>()
    }.mapNotNull { kProperty ->
        val artifactName = if (kProperty.isConst) {
            kProperty.javaField!!.get(null).toString()
        } else {
            @Suppress("unchecked_cast")
            (kProperty as KProperty1<Any?, String>).get(objectInstance)
        }.let { value ->
            val columnCount = value.count { it == ':' }
            if (columnCount < if (excludeBomDependencies) 2 else 1) return@mapNotNull null
            val hasVersion = columnCount == 2
            if (hasVersion) {
                value.substringBeforeLast(':') // Before version delimiter.
            } else value
        }
        val constantName = "$prefix.${kProperty.name}"
        val group = artifactName.substringBefore(':')
        val name = artifactName.substringAfter(':')
        DependencyMapping(
            group = group,
            artifact = name,
            constantName = constantName
        )
    }
}
