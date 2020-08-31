@file:Suppress("DeprecatedCallableAddReplaceWith")

package org.gradle.kotlin.dsl

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

/**
 * Marker interface that prevents the user to add a wrong dependency like
 *     implementation(AndroidX.core)
 * Since AndroidX.core IsNotADependency, the IDE will show a warning
 */
interface IsNotADependency

const val ErrorIsNotADependency = """This is not a valid dependency notation but a group of dependency notations"""

@kotlin.Deprecated(ErrorIsNotADependency)
fun DependencyHandler.`implementation`(dependencyNotation: IsNotADependency): Dependency? =
    null

@kotlin.Deprecated(ErrorIsNotADependency)
fun DependencyHandler.`api`(dependencyNotation: IsNotADependency): Dependency? =
    null

@kotlin.Deprecated(ErrorIsNotADependency)
fun DependencyHandler.`testImplementation`(dependencyNotation: IsNotADependency): Dependency? =
    null
