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
import Website_main.Util.step
import Website_main.Util.named
import Website_main.Util.run


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
        run(Steps.docsCopier())
        step(Steps.setupPython())
        run(Steps.pipInstall())
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
        run(Steps.docsCopier())
        step(Steps.setupPython())
        run(Steps.pipInstall())
        run("publish-website", "mkdocs gh-deploy --force")
    }
}


listOf(buildMkdocs, publishMkdocs)
    .forEach { workflow ->
        println("Updating ${workflow.targetFile}")
        workflow.writeToFile()
    }



object Steps {

    fun checkout(branch: String? = null, fetchDepth: CheckoutV2.FetchDepth? = null): Step =
        CheckoutV2(
            ref = branch,
            fetchDepth = fetchDepth
        ).named("check-out")

    fun docsCopier(): Command =
        Command("kotlin-script", "./docs/DocsCopier.main.kts")

    fun pipInstall(): Command =
        Command("pip-install", "pip install -r docs/requirements.txt")

    fun setupPython(): Step = SetupPythonV2(
            pythonVersion = "3.x"
        ).named("setup-python")
}

/*******************/
// Hopefully this will become part of github-actions-kotlin-dsl

data class Step(
    val name: String,
    val action: Action,
    val env: Map<String, String> = emptyMap(),
    val condition: String? = null
) {
    fun env(env: Map<String, String>) = copy(env = env)
    fun condition(condition: String?) = copy(condition = condition)
}

data class Command(
    val name: String,
    val command: String,
    val env: Map<String, String> = emptyMap(),
    val condition: String? = null,
) {
    fun env(env: Map<String, String>) = copy(env = env)
    fun condition(condition: String?) = copy(condition = condition)
}

// Kotlin Scripts do not support using top-level functions in objects
// See: https://youtrack.jetbrains.com/issue/KT-51329
object Util {
    fun requireRegex(input: String, regex: String) {
        require(input.matches(Regex(regex))) { "Invalid input=[$input] does not mach regex $regex" }
    }

    fun secret(variable: String): String {
        requireRegex(variable, "[A-Z_-]+")
        return "\${{ secrets.$variable }}"
    }

    fun env(variable: String): String {
        requireRegex(variable, "[A-Z_-]+")
        return "\$$variable"
    }

    fun JobBuilder.step(name: String, env: Map<String, String> = emptyMap(), action: () -> Action) =
        uses(name, action(), LinkedHashMap(env))

    fun JobBuilder.step(step: Step) =
        uses(step.name, step.action, LinkedHashMap(step.env), step.condition)

    fun Action.named(name: String) = Step(name, this)

    fun JobBuilder.run(command: Command) =
        run(command.name, command.command, LinkedHashMap(command.env), command.condition)
}