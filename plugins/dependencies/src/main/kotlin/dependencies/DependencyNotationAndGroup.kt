package dependencies

abstract class DependencyNotationAndGroup(
    group: String,
    name: String
) : CharSequence {

    protected val artifactPrefix = "$group:$name"

    internal val backingString = "$artifactPrefix:_"
    override val length get() = backingString.length
    override fun get(index: Int) = backingString.get(index)

    override fun subSequence(
        startIndex: Int,
        endIndex: Int
    ) = backingString.subSequence(startIndex = startIndex, endIndex = endIndex)

    override fun toString(): String = backingString
}
