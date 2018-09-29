package io.github.jmfayard

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.invoke
import org.gradle.kotlin.dsl.register

open class GradleKotlinDslLibsPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.run {

            tasks {
                register("jmf", DefaultTask::class) {
                    doLast {
                        println("echo ok")
                    }
                }
            }
        }
    }
}
