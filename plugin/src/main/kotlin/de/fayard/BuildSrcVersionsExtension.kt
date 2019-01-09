package de.fayard

import org.gradle.api.Project
import org.gradle.api.provider.Property
import org.gradle.kotlin.dsl.property

@Suppress("UnstableApiUsage")
open class BuildSrcVersionsExtension(project: Project) {
    /**
     * Use the FDQN name for this set of dependencies. Libs.com_example_something_core instead of Libs.core
     */
    val useFdqnFor: Property<Iterable<String>> = project.objects.property()
}
