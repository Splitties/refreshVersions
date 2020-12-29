package de.fayard.refreshVersions.core.extensions.sequences

internal fun <T, K> Sequence<T>.uniqueBy(
    onDuplicate: (key: K) -> Nothing = { key ->
        throw IllegalArgumentException(
            "Sequence contains more than one element with the key $key."
        )
    },
    selector: (T) -> K
): Sequence<T> {
    return UniqueSequence(this, onDuplicate, selector)
}

private class UniqueSequence<T, K>(
    private val source: Sequence<T>,
    private val onDuplicateFound: (key: K) -> Nothing,
    private val keySelector: (T) -> K
) : Sequence<T> {

    override fun iterator(): Iterator<T> = object : AbstractIterator<T>() {
        private val observed = HashSet<K>()
        private val sourceIterator = source.iterator()

        override fun computeNext() {
            while (sourceIterator.hasNext()) {
                val next = sourceIterator.next()
                val key = keySelector(next)

                if (observed.add(key)) {
                    setNext(next)
                    return
                } else {
                    onDuplicateFound(key)
                }
            }

            done()
        }
    }
}
