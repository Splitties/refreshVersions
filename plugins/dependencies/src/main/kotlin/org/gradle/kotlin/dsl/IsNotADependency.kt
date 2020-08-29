@file:Suppress("DeprecatedCallableAddReplaceWith")

package org.gradle.kotlin.dsl

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

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
