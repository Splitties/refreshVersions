@file:Suppress("INVISIBLE_REFERENCE", "INVISIBLE_MEMBER") //TODO: Remove when https://youtrack.jetbrains.com/issue/KT-34102 is fixed.

package testutils

import de.fayard.refreshVersions.core.internal.removals_replacement.parseRemovedDependencyNotationsHistory

import java.io.File

fun parseRemovedDependencyNotations(file: File) {
    file.inputStream().parseRemovedDependencyNotationsHistory(currentRevision = 0)
}
