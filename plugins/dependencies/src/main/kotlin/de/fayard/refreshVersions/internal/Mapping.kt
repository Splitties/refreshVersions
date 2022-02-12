package de.fayard.refreshVersions.internal

import de.fayard.refreshVersions.core.AbstractDependencyGroup
import de.fayard.refreshVersions.core.DependencyNotation
import de.fayard.refreshVersions.core.internal.DependencyMapping
import dependencies.ALL_DEPENDENCIES_NOTATIONS
import org.gradle.api.artifacts.ModuleIdentifier
import java.lang.reflect.Field
import kotlin.reflect.KProperty
import kotlin.reflect.KProperty1
import kotlin.reflect.KVisibility
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.javaType
import kotlin.reflect.typeOf

internal fun getArtifactNameToConstantMapping(excludeBomDependencies: Boolean = false): List<DependencyMapping> {
    AbstractDependencyGroup.disableBomCheck = true
    val result = ALL_DEPENDENCIES_NOTATIONS.asSequence().flatMap { objectInstance ->
        getArtifactNameToConstantMappingFromObject(
            objectInstance,
            excludeBomDependencies = excludeBomDependencies,
            isTopLevelObject = true
        )
    }.sortedBy { it.toString() }.toList()
    AbstractDependencyGroup.disableBomCheck = false
    return result
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
    }.toList()
}

private fun getArtifactNameToConstantMappingFromObject(
    objectInstance: Any,
    prefix: String = with(objectInstance::class) {
        if (isCompanion) qualifiedName!!.removeSuffix(".Companion") else qualifiedName!!
    },
    excludeBomDependencies: Boolean = false,
    isTopLevelObject: Boolean = false
): Sequence<DependencyMapping> {
    val objectClass = objectInstance::class

    val mappingOfNestedObjects = objectClass.memberProperties.asSequence().filter { kProperty ->
        @OptIn(ExperimentalStdlibApi::class)
        kProperty.visibility == KVisibility.PUBLIC && kProperty.returnType.let {
            // Filter out dependency constants and redirection properties.
            it != typeOf<String>() && it.javaType != java.lang.Void::class.java
        }
    }.flatMap { kProperty ->
        when (kProperty.name) {
            "rule", "length", "externalImplementationGuard", "usePlatformConstraints" -> return@flatMap emptySequence()
        }
        @Suppress("unchecked_cast")
        val nestedObjectInstance = (kProperty as KProperty1<Any?, Any>).get(objectInstance)
        getArtifactNameToConstantMappingFromObject(
            objectInstance = nestedObjectInstance,
            prefix = "$prefix.${kProperty.name}",
            excludeBomDependencies = excludeBomDependencies
        )
    }

    @OptIn(ExperimentalStdlibApi::class)
    val currentObjectDependencyNotations: Sequence<Pair<KProperty<*>?, String>> =
        objectClass.memberProperties.asSequence().filter { kProperty ->
            kProperty.visibility == KVisibility.PUBLIC && kProperty.returnType.let {
                (it == typeOf<String>() || it.isSubtypeOf(typeOf<DependencyNotation>())) &&
                        it.javaType != java.lang.Void::class.java && // Filter out redirection properties.
                kProperty.name != "artifactPrefix"
            }
        }.map { kProperty ->
            val javaField: Field? = kProperty.javaField

            @OptIn(ExperimentalStdlibApi::class)
            @Suppress("unchecked_cast")
            val dependencyNotation = when {
                kProperty.returnType.isSubtypeOf(typeOf<DependencyNotation>()) -> {
                    (kProperty as KProperty1<Any?, DependencyNotation>).get(objectInstance).toString()
                }
                kProperty.isConst -> javaField!!.get(null).toString()
                else -> try {
                    (kProperty as KProperty1<Any?, String>).get(objectInstance)
                } catch (e: IllegalArgumentException) {
                    javaField!!.get(objectInstance).toString()
                }
            }
            kProperty to dependencyNotation
        }

    val dependencyNotations = if (isTopLevelObject && objectInstance is DependencyNotation) {
        currentObjectDependencyNotations + (null to objectInstance.toString())
    } else currentObjectDependencyNotations

    val currentObjectDependencyMapping = dependencyNotations.mapNotNull { (kProperty, dependencyNotation) ->
        val artifactName = dependencyNotation.let { value ->
            val columnCount = value.count { it == ':' }
            if (columnCount < if (excludeBomDependencies) 2 else 1) return@mapNotNull null
            val hasVersion = columnCount == 2
            if (hasVersion) {
                value.substringBeforeLast(':') // Before version delimiter.
            } else value
        }
        val constantName = if (kProperty == null) {
            objectClass.simpleName!!
        } else {
            "$prefix.${kProperty.name}"
        }
        val group = artifactName.substringBefore(':')
        val name = artifactName.substringAfter(':')
        DependencyMapping(
            group = group,
            artifact = name,
            constantName = constantName
        )
    }

    return mappingOfNestedObjects + currentObjectDependencyMapping
}
