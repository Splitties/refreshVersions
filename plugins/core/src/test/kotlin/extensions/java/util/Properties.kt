package extensions.java.util

import java.io.File
import java.util.*

fun Properties.loadAndGetAsMap(file: File): Map<String, String> {
    load(file.inputStream())
    @Suppress("unchecked_cast")
    return this as Map<String, String>
}
