package de.fayard.versions.internal

internal sealed class ArtifactPatternPart {
    data class Word(val value: String) : ArtifactPatternPart() {
        init {
            require(value.all { it.isLetterOrDigit() })
        }
    }
    object Dot : ArtifactPatternPart() // .
    object Dash : ArtifactPatternPart() // -
    object WordPlaceholder : ArtifactPatternPart() // ???
    object SuffixPlaceholder : ArtifactPatternPart() // *
    object OptionalDashedSuffixPlaceholder : ArtifactPatternPart() // (-*)
}
