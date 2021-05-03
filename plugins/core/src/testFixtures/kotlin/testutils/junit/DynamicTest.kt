package testutils.junit

import org.junit.jupiter.api.DynamicTest
import java.io.File

fun dynamicTest(displayName: String, block: () -> Unit): DynamicTest {
    return DynamicTest.dynamicTest(displayName, block)
}

fun List<File>.mapDynamicTest(
    name: (File) -> String = { it.name },
    block: (File) -> Unit
): List<DynamicTest> = map { file ->
    dynamicTest(displayName = name(file), block = { block(file) })
}
