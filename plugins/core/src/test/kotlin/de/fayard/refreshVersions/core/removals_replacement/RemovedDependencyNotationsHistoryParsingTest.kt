package de.fayard.refreshVersions.core.removals_replacement

import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.internal.removals_replacement.RemovedDependencyNotation
import de.fayard.refreshVersions.core.internal.removals_replacement.parseRemovedDependencyNotationsHistory
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import testutils.junit.dynamicTest
import kotlin.test.Test
import kotlin.test.assertFailsWith

class RemovedDependencyNotationsHistoryParsingTest {

    @Test
    fun `test parseRemovedDependencyNotationsHistory`() {
        val testData = """
            ## Revision 1

            ~~AndroidX.wear.watchFace.client~~
            //FIXME: Replace with the new dependency and remove these comments.
            moved:[androidx.wear.watchface:watchface-client]
            id:[androidx.wear:wear-watchface-client]

            ## Revision 2
            ~~SomeGroup.something~~
            id:[com.somegroup:somegroup-something]

            ## Revision 3
            ~~SomeGroup.one~~
            //FIXME: Remove dependency now that somegroup one has been deprecated.
            id:[com.somegroup:somegroup-one]
            ~~SomeGroup.two~~
            moved:[com.anothergroup:anothergroup-two]
            id:[com.somegroup:somegroup-two]

            ~~SomeGroup.three~~
            moved:[com.anothergroup:anothergroup-three]
            extra:[com.anothergroup:anothergroup-three-plus]
            id:[com.somegroup:somegroup-three]
        """.trimIndent()
        val expectedValues = object {
            val watchFaceClient = RemovedDependencyNotation(
                dependencyNotation = "AndroidX.wear.watchFace.client",
                moduleId = ModuleId.Maven(group = "androidx.wear", name = "wear-watchface-client"),
                leadingCommentLines = listOf(
                    "//FIXME: Replace with the new dependency and remove these comments."
                ),
                replacementMavenCoordinates = ModuleId.Maven(
                    group = "androidx.wear.watchface", name = "watchface-client"
                )
            )
            val something = RemovedDependencyNotation(
                dependencyNotation = "SomeGroup.something",
                moduleId = ModuleId.Maven(group = "com.somegroup", name = "somegroup-something"),
                leadingCommentLines = emptyList(),
                replacementMavenCoordinates = emptyList()
            )
            val one = RemovedDependencyNotation(
                dependencyNotation = "SomeGroup.one",
                moduleId = ModuleId.Maven(group = "com.somegroup", name = "somegroup-one"),
                leadingCommentLines = listOf(
                    "//FIXME: Remove dependency now that somegroup one has been deprecated."
                ),
                replacementMavenCoordinates = emptyList()
            )
            val two = RemovedDependencyNotation(
                dependencyNotation = "SomeGroup.two",
                moduleId = ModuleId.Maven(group = "com.somegroup", name = "somegroup-two"),
                leadingCommentLines = emptyList(),
                replacementMavenCoordinates = ModuleId.Maven(
                    group = "com.anothergroup", name = "anothergroup-two"
                )
            )
            val three = RemovedDependencyNotation(
                dependencyNotation = "SomeGroup.three",
                moduleId = ModuleId.Maven(group = "com.somegroup", name = "somegroup-three"),
                leadingCommentLines = emptyList(),
                replacementMavenCoordinates = listOf(
                    ModuleId.Maven(group = "com.anothergroup", name = "anothergroup-three"),
                    ModuleId.Maven(group = "com.anothergroup", name = "anothergroup-three-plus")
                )
            )
        }
        listOf(
            0 to listOf(
                expectedValues.watchFaceClient,
                expectedValues.something,
                expectedValues.one,
                expectedValues.two,
                expectedValues.three
            ),
            1 to listOf(
                expectedValues.something,
                expectedValues.one,
                expectedValues.two,
                expectedValues.three
            ),
            2 to listOf(
                expectedValues.one,
                expectedValues.two,
                expectedValues.three
            ),
            3 to emptyList()
        ).forEach { (currentRevision, expectedList) ->
            testData.lineSequence().parseRemovedDependencyNotationsHistory(
                currentRevision = currentRevision
            ).joinToString(separator = "\n") shouldBe expectedList.joinToString(separator = "\n")
        }
    }

    @TestFactory
    fun `incorrect revision order should fail to parse`(): List<DynamicTest> {
        return listOf(
            """
            ## Revision 2

            ~~AndroidX.wear.watchFace.client~~
            //FIXME: Replace with the new dependency and remove these comments.
            moved:[androidx.wear.watchface:watchface-client]
            id:[androidx.wear:wear-watchface-client]
        """.trimIndent(),
            """
            ## Revision 2

            ~~AndroidX.wear.watchFace.client~~
            //FIXME: Replace with the new dependency and remove these comments.
            moved:[androidx.wear.watchface:watchface-client]
            id:[androidx.wear:wear-watchface-client]

            ## Revision 3
            ~~SomeGroup.something~~
            id:[com.somegroup:somegroup-something]

            ## Revision 4
            ~~SomeGroup.one~~
            //FIXME: Remove dependency now that somegroup one has been deprecated.
            id:[com.somegroup:somegroup-one]
            ~~SomeGroup.two~~
            moved:[com.anothergroup:anothergroup-two]
            id:[com.somegroup:somegroup-two]
        """.trimIndent(),
            """
            ## Revision 1

            ~~AndroidX.wear.watchFace.client~~
            //FIXME: Replace with the new dependency and remove these comments.
            moved:[androidx.wear.watchface:watchface-client]
            id:[androidx.wear:wear-watchface-client]

            ## Revision 3
            ~~SomeGroup.something~~
            id:[com.somegroup:somegroup-something]

            ## Revision 4
            ~~SomeGroup.one~~
            //FIXME: Remove dependency now that somegroup one has been deprecated.
            id:[com.somegroup:somegroup-one]
            ~~SomeGroup.two~~
            moved:[com.anothergroup:anothergroup-two]
            id:[com.somegroup:somegroup-two]
        """.trimIndent()
        ).mapIndexed { index: Int, testData: String ->
            dynamicTest("testData #${index + 1}") {
                assertFailsWith<IllegalArgumentException> {
                    testData.lineSequence().parseRemovedDependencyNotationsHistory(currentRevision = null)
                }
            }
        }
    }
}
