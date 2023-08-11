package de.fayard.refreshVersions.core.internal.cli

import de.fayard.refreshVersions.core.internal.InternalRefreshVersionsApi
import java.util.Scanner

@InternalRefreshVersionsApi
interface CliGenericUi {

    companion object {
        operator fun invoke(): CliGenericUi =
            CliGenericUiImpl()
    }

    fun askBinaryQuestion(
        question: String,
        trueChoice: String,
        falseChoice: String
    ): Boolean

    fun showMenuAndGetIndexOfChoice(
        header: String,
        footer: String,
        numberedEntries: List<String>
    ): MenuEntryIndex
}

@InternalRefreshVersionsApi
inline class MenuEntryIndex(val value: Int)

private class CliGenericUiImpl : CliGenericUi {

    override fun askBinaryQuestion(
        question: String,
        trueChoice: String,
        falseChoice: String
    ): Boolean {
        while (true) {
            print(AnsiColor.WHITE.boldHighIntensity)
            print(AnsiColor.BLUE.background)
            print(question)
            println(AnsiColor.RESET)
            print(AnsiColor.WHITE.boldHighIntensity)
            print(AnsiColor.GREEN.background)
            print("$trueChoice/$falseChoice")
            println(AnsiColor.RESET)
            when (scanner.nextLine()) {
                trueChoice -> return true
                falseChoice -> return false
                else -> {
                    print(AnsiColor.RED.bold)
                    print("Unexpected input, please, try again")
                    println(AnsiColor.RESET)
                }
            }
        }
    }

    override fun showMenuAndGetIndexOfChoice(
        header: String,
        footer: String,
        numberedEntries: List<String>
    ): MenuEntryIndex {
        println(header)
        println()
        numberedEntries.forEachIndexed { index, entryText ->
            val number = index + 1
            print("$number. ")
            println(entryText)
        }
        println()
        print(AnsiColor.WHITE.boldHighIntensity)
        print(AnsiColor.BLUE.background)
        print(footer)
        println(AnsiColor.RESET)
        return MenuEntryIndex(scanner.nextInt() - 1)
    }

    private val scanner = Scanner(System.`in`)
}
