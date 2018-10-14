package jmfayard.github.io

import org.intellij.lang.annotations.Language
import java.io.File

fun testResourceFile(@Language("File") path: String, write: Boolean = false): File {
    SyncLibsTask::class
    return File("src/test/resources/$path").apply {
        val condition = if (write) canWrite() else canRead()
        check(condition) { "Cannot open resourceFile at $absolutePath" }
    }
}


fun List<String>.joinToStringWithNewLines(): String =
    joinToString(separator = "\n", prefix = "", postfix = "")


