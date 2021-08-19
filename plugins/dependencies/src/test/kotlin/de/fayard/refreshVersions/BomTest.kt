package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.DependencyNotation
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class BomTest {

    @Test
    fun `should fail when related BoM is used after dependencies from the group`() {
        Square.okHttp3.reset()
        use(Square.okHttp3.okHttp)
        assertFailsWith<IllegalStateException> {
            use(Square.okHttp3.bom)
        }
    }

    @Test
    fun `should succeed when related BoM is used before dependencies from the group`() {
        Square.okHttp3.reset()
        use(Square.okHttp3.bom)
        use(Square.okHttp3.okHttp)
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
