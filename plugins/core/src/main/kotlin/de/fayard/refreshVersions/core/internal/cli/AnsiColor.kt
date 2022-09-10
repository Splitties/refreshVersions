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
        private val isCompatible = "win" !in System.getProperty("os.name").toLowerCase() //TODO: Support PowerShell?
        private inline fun ifCompatible(block: () -> String): String = if (isCompatible) block() else ""

        val RESET = ifCompatible { "$prefix[0m" }

        val bold get() = ifCompatible { "$prefix[1m" }
        val dim get() = ifCompatible { "$prefix[2m" }
        val italic get() = ifCompatible { "$prefix[3m" }
        val underline get() = ifCompatible { "$prefix[4m" }
        val blinking get() = ifCompatible { "$prefix[5m" }
        val inverse get() = ifCompatible { "$prefix[7m" }
        val invisible get() = ifCompatible { "$prefix[8m" }
    }

    override fun toString() = regular
    val regular get() = ifCompatible { "$prefix[0;3${colorNumber}m" }
    val bold get() = ifCompatible { "$prefix[1;3${colorNumber}m" }
    val underline get() = ifCompatible { "$prefix[4;3${colorNumber}m" }
    val background get() = ifCompatible { "$prefix[4${colorNumber}m" }
    val highIntensity get() = ifCompatible { "$prefix[0;9${colorNumber}m" }
    val boldHighIntensity get() = ifCompatible { "$prefix[1;9${colorNumber}m" }
    val backgroundHighIntensity get() = ifCompatible { "$prefix[0;10${colorNumber}m" }
}
