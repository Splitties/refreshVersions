package de.fayard

import de.fayard.internal.AvailableDependency
import de.fayard.internal.Dependency
import de.fayard.internal.VersionMode
import org.intellij.lang.annotations.Language
import java.io.File

fun testResourceFile(@Language("File") path: String, write: Boolean = false) : File =
    testResourcesDir.resolve(path).apply {
        val condition = if (write) canWrite() else canRead()
        check(condition) { "Cannot open resourceFile at $absolutePath" }
    }


fun List<String>.joinToStringWithNewLines(): String =
    joinToString(separator = "\n", prefix = "", postfix = "")

val testResourcesDir: File
    get() = File("src/test/resources").absoluteFile

val refreshVersionsDir: File
    get() = File(".").absoluteFile.parentFile.parentFile

/**
 *  Usage:
 *
 *  ```kotlin
 *  "com.github.ben-manes:gradle-versions-plugin:0.22.0 // 0.25.0".asDependency()
 *  ```
 * **/
fun String.asDependency(fqdn: Boolean = false): Dependency {
    val available = this.substringAfter("// ", "").trim()
    val rest = this.substringBefore(" // ")
    val (group, name, version) = rest.split(":")
    return Dependency(
        group = group,
        version = version,
        reason = "",
        latest = available,
        projectUrl = "",
        name = name,
        escapedName = name,
        mode = if (fqdn) VersionMode.GROUP_MODULE else VersionMode.MODULE,
        available = if (available.isBlank()) null else AvailableDependency(release = available)
    )
}
