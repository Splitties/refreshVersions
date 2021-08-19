package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.DependencyNotation
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import kotlin.test.assertFailsWith

@TestMethodOrder(MethodOrderer.MethodName::class)
class BomTest {

    @Test
    fun `should fail when related BoM is used after dependencies from the group`() {
        Square.okHttp3.reset()
        use(Square.okHttp3)
        assertFailsWith<IllegalStateException> {
            use(Square.okHttp3.bom)
        }
    }

    @Test
    fun `should succeed when related BoM is used before dependencies from the group`() {
        Square.okHttp3.reset()
        use(Square.okHttp3.bom)
        use(Square.okHttp3)
    }

    @Test
    fun `should fail when using a dependency from a group with enforced BoM, without depending on the BoM first`() {
        return // We don't enforce BoM yet.
        assertFailsWith<IllegalStateException> {
            use(Firebase.analyticsKtx)
        }
    }

    private fun use(dependencyNotation: DependencyNotation) {
        dependencyNotation.toString()
    }
}
