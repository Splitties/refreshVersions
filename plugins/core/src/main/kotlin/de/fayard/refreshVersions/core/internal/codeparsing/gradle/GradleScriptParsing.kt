package de.fayard.refreshVersions.core.internal.codeparsing.gradle

import de.fayard.refreshVersions.core.internal.TaggedRange
import de.fayard.refreshVersions.core.internal.codeparsing.ProgrammingLanguage
import de.fayard.refreshVersions.core.internal.codeparsing.SourceCodeSection
import de.fayard.refreshVersions.core.internal.codeparsing.findRanges

internal fun CharSequence.extractGradleScriptSections(
    isKotlinDsl: Boolean
): List<TaggedRange<SourceCodeSection>> = findRanges(
    programmingLanguage = when {
        isKotlinDsl -> ProgrammingLanguage.Kotlin
        else -> ProgrammingLanguage.Groovy
    }
)
