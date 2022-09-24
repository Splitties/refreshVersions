package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.Case
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe

class CaseTest : StringSpec({
    val transformations = listOf(
        "hoplite_yaml" to "hoplite-yaml",
        "picnic" to "picnic",
        "h2_ok" to "h2-ok",
        "mockito_kotlin" to "mockito-kotlin",
        "retrofit2_kotlinx_serialization_converter" to "retrofit2-kotlinx-serialization-converter",
        "dagger_compiler" to "dagger-compiler"
    )

    "From snake_case to kebab-case" {
        transformations.forAll { (snake, kebab) ->
            Case.`kebab-case`.convert(snake) shouldBe kebab
        }
    }

})
