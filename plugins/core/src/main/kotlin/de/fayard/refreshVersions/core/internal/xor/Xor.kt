package de.fayard.refreshVersions.core.internal.xor

import de.fayard.refreshVersions.core.internal.FunctionalCore
import de.fayard.refreshVersions.core.internal.NeedsRefactoring

/**
 * A class similar to `Either` from Arrow, but with less features (because some are not needed).
 *
 * XOR stands for eXclusive OR.
 */
@FunctionalCore(testName = "TODO")
@NeedsRefactoring("Either without .map() and .flatmap() isn't really useful")
internal sealed class Xor<out FirstT, out SecondT> {

    fun firstOrNull(): FirstT? = if (this is First) this.value else null
    fun secondOrNull(): SecondT? = if (this is Second) this.value else null

    data class First<LeftT>(val value: LeftT) : Xor<LeftT, Nothing>()

    data class Second<RightT>(val value: RightT) : Xor<Nothing, RightT>()
}
