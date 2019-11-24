package de.fayard.dependencies.internal

import dependencies.*
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
            val (key, constantName) = line.split("=").takeIf { it.size == 2 } ?: return null
            val (group, artifact) = key.split("..").takeIf { it.size == 2 } ?: return null
            return DependencyMapping(group, artifact, constantName)
        }
    }
    override fun toString(): String = "$group..$artifact=$constantName"
}


fun getArtifactNameToSplittiesConstantMapping(): List<DependencyMapping> {
    return listOf(AndroidX, Google, Kotlin, KotlinX, Splitties, Square, Testing).flatMap { objectInstance ->
        (objectInstance::class).getArtifactNameToSplittiesConstantMapping(objectInstance::class.simpleName!!)
    }.sortedBy { it.toString() }
}

@UseExperimental(ExperimentalStdlibApi::class)
private fun KClass<*>.getArtifactNameToSplittiesConstantMapping(prefix: String): List<DependencyMapping> {
    return nestedClasses.filter { it.visibility == KVisibility.PUBLIC }.flatMap { kClass ->
        val propertyName = kClass.simpleName!!.let { c -> "${c.first().toLowerCase()}${c.substring(1)}"}
        kClass.getArtifactNameToSplittiesConstantMapping("$prefix.$propertyName")
    } + this.memberProperties.filter {
        it.isConst &&
            it.visibility == KVisibility.PUBLIC &&
            it.returnType == typeOf<String>()
    }.map {
        val (group, artifact) = it.javaField!!.get(null).toString().substringBeforeLast(':').split(":")
        val constantName = "$prefix.${it.name}"
        DependencyMapping(group, artifact, constantName)
    }
}
