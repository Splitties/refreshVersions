package de.fayard.refreshVersions.core.internal.cli

import de.fayard.refreshVersions.core.internal.InternalRefreshVersionsApi

@InternalRefreshVersionsApi
enum class AnsiColor(private val colorNumber: Byte) {
    BLACK(0),
    RED(1),
    GREEN(2),
    YELLOW(3),
    BLUE(4),
    MAGENTA(5),
    CYAN(6),
    WHITE(7);

    companion object {
        private const val prefix = "\u001B"
        const val RESET = "$prefix[0m"
        private val isCompatible = "win" !in System.getProperty("os.name").toLowerCase() //TODO: Support PowerShell?
    }

    val regular get() = if (isCompatible) "$prefix[0;3${colorNumber}m" else ""
    val bold get() = if (isCompatible) "$prefix[1;3${colorNumber}m" else ""
    val underline get() = if (isCompatible) "$prefix[4;3${colorNumber}m" else ""
    val background get() = if (isCompatible) "$prefix[4${colorNumber}m" else ""
    val highIntensity get() = if (isCompatible) "$prefix[0;9${colorNumber}m" else ""
    val boldHighIntensity get() = if (isCompatible) "$prefix[1;9${colorNumber}m" else ""
    val backgroundHighIntensity get() = if (isCompatible) "$prefix[0;10${colorNumber}m" else ""
}
