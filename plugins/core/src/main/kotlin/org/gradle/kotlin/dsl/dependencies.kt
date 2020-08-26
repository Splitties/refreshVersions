package org.gradle.kotlin.dsl

import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.`implementations`(vararg dependencies: String) =
    implementations(dependencies.toList())

fun DependencyHandler.`apis`(vararg dependencies: String) =
    apis(dependencies.toList())

fun DependencyHandler.`testImplementations`(vararg dependencies: String) =
    testImplementations(dependencies.toList())

fun DependencyHandler.`implementations`(dependencies: Iterable<String>) =
    dependencies.forEach { dependencyNotation ->
        add("implementation", dependencyNotation)
    }

fun DependencyHandler.`apis`(dependencies: Iterable<String>) =
    dependencies.forEach { dependencyNotation ->
        add("api", dependencyNotation)
    }

fun DependencyHandler.`testImplementations`(dependencies: Iterable<String>) =
    dependencies.forEach { dependencyNotation ->
        add("testImplementation", dependencyNotation)
    }
