#!/usr/bin/env kotlin

@file:DependsOn("it.krzeminski:github-actions-kotlin-dsl:0.8.0")

import it.krzeminski.githubactions.actions.Action
import it.krzeminski.githubactions.actions.actions.CheckoutV2
import it.krzeminski.githubactions.actions.actions.SetupPythonV2
import it.krzeminski.githubactions.actions.actions.UploadArtifactV2
import it.krzeminski.githubactions.domain.RunnerType
import it.krzeminski.githubactions.domain.triggers.Push
import it.krzeminski.githubactions.domain.triggers.WorkflowDispatch
import it.krzeminski.githubactions.dsl.JobBuilder
import it.krzeminski.githubactions.dsl.workflow
import it.krzeminski.githubactions.yaml.writeToFile
import java.nio.file.Path
import java.nio.file.Paths


object Files {
    val source: Path = Paths.get("website.main.kts")
    val buildMkdocs: Path = Paths.get("build-mkdocs-website.yml")
    val publishMkdocs: Path = Paths.get("publish-mkdocs-website.yml")
}


val buildMkdocs = workflow(
    name = "Build MkDocs website as artifacts",
    targetFile = Files.buildMkdocs,
    sourceFile = Files.source,
    on = listOf(
        Push(
            branchesIgnore = listOf("release"),
        ),
    ),
) {

    job("build-mkdocs", RunnerType.UbuntuLatest) {

        step(Steps.checkout())
        run(Steps.docsCopier)
        step(Steps.setupPython)
        run(Steps.pipInstall)
        run("build-website", "mkdocs build --site-dir public")
        step("upload-website") {
            UploadArtifactV2(name = "docs-static-website", path = listOf("public"))
        }
    }
}

val publishMkdocs = workflow(
    name = "Publish MkDocs website to GitHub pages",
    targetFile = Files.publishMkdocs,
    sourceFile = Files.source,
    on = listOf(
        Push(branches = listOf("release")),
        WorkflowDispatch(),
    ),
) {

    job("deploy-mkdocs", RunnerType.UbuntuLatest) {

        step(Steps.checkout())
        run(Steps.docsCopier)
        step(Steps.setupPython)
        run(Steps.pipInstall)
        run("publish-website", "mkdocs gh-deploy --force")
    }
}


listOf(buildMkdocs, publishMkdocs)
    .forEach { workflow ->
        println("Updating ${workflow.targetFile}")
        workflow.writeToFile()
    }



object Steps {

    fun checkout(branch: String? = null, fetchDepth: CheckoutV2.FetchDepth? = null) = Step(
        "check-out",
        CheckoutV2(
            ref = branch, fetchDepth = fetchDepth
        )
    )

    val docsCopier = Command("kotlin-script", "./docs/DocsCopier.main.kts")

    val pipInstall = Command("pip-install", "pip install -r docs/requirements.txt")

    val setupPython = Step(
        "setup-python",
        SetupPythonV2(
            pythonVersion = "3.x"
        )
    )

}

/***
 * Below this comment are things that should hopefully become part of kotlin-dsl-github-actions
 */

data class Step(val name: String, val action: Action)

data class Command(val name: String, val command: String)

fun JobBuilder.step(
    name: String,
    env: Map<String, String> = emptyMap(),
    condition: String? = null,
    action: () -> Action,
) =
    uses(name, action(), LinkedHashMap(env), condition)

fun JobBuilder.step(step: Step, env: Map<String, String> = emptyMap(), condition: String? = null) =
    uses(step.name, step.action, LinkedHashMap(env), condition)

fun JobBuilder.run(command: Command, env: Map<String, String> = emptyMap(), condition: String? = null) =
    run(command.name, command.command, LinkedHashMap(env), condition)

