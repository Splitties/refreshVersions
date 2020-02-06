package de.fayard.dependencies.internal

import AndroidX
import Google
import JakeWharton
import Kotlin
import KotlinX
import Splitties
import Square
import Testing
import kotlin.reflect.KClass
import kotlin.reflect.KVisibility
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaField
import kotlin.reflect.typeOf

data class DependencyMapping(
    val group: String,
    val artifact: String,
    val constantName: String
) {
    companion object {
        fun fromLine(line: String) : DependencyMapping? {
            if (line.isEmpty()) return null
            val (key, constantName) = line.split("=").takeIf { it.size == 2 } ?: return null
            val (group, artifact) = key.split("..").takeIf { it.size == 2 } ?: return null
            return DependencyMapping(group, artifact, constantName)
        }
    }
    override fun toString(): String = "$group..$artifact=$constantName"
}


fun getArtifactNameToConstantMapping(): List<DependencyMapping> {
    return listOf(
        AndroidX,
        Google,
        JakeWharton,
        Kotlin,
        KotlinX,
        Splitties,
        Square,
        Testing
    ).flatMap { objectInstance ->
        (objectInstance::class).getArtifactNameToConstantMapping(objectInstance::class.simpleName!!)
    }.sortedBy { it.toString() }
}

@UseExperimental(ExperimentalStdlibApi::class)
private fun KClass<*>.getArtifactNameToConstantMapping(prefix: String): List<DependencyMapping> {
    return nestedClasses.filter { it.visibility == KVisibility.PUBLIC }.flatMap { kClass ->
        val propertyName = kClass.simpleName!!.let { c -> "${c.first().toLowerCase()}${c.substring(1)}"}
        kClass.getArtifactNameToConstantMapping("$prefix.$propertyName")
    } + this.memberProperties.filter {
        it.isConst &&
            it.visibility == KVisibility.PUBLIC &&
            it.returnType == typeOf<String>()
    }.map {
        val artifactName = it.javaField!!.get(null).toString().substringBeforeLast(':') // Before version delimiter.
        val constantName = "$prefix.${it.name}"
        val group = artifactName.substringBefore(':')
        val name = artifactName.substringAfter(':')
        DependencyMapping(
            group = group,
            artifact = name,
            constantName = constantName
        )
    }
}
