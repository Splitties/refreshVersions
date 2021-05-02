package de.fayard.refreshVersions.core.internal.codeparsing

internal enum class SourceCodeSection {
    Comment,
    StringLiteral,

    /** The code chunk will be cut-off on any string literal or comment start. */
    CodeChunk
}
