package extensions.kotlin.collections

fun <E> List<E>.subListAfter(element: E): List<E> = when (val indexOfElement = indexOf(element)) {
    -1 -> this
    lastIndex -> emptyList()
    else -> subList(fromIndex = indexOfElement + 1, toIndex = size)
}
