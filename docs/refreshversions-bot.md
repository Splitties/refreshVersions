Bots like [dependabot](https://github.com/dependabot) and [renovabot](https://docs.renovatebot.com/)
take another approach to help you upgrade your dependencies:
they can't be used locally on your machine, but they do send you pull requests proposing you to update your dependencies.

If you like this approach, a simple GitHub Actions workflow allows you to do the same with refreshVersions.

<img width="951" alt="Upgrade_gradle_dependencies_by_github-actions_bot__·_Pull_Request__327_·_TIGNUM_backend-tignum-x" src="https://user-images.githubusercontent.com/459464/182308182-49769e99-8857-4072-bdaa-9af94a4d0ea3.png">

Once a week, it will run refreshVersions, commit the changes to `versions.properties` and other files,
create a commit and a pull request. Now you can't forget to run refreshVersions :)

This is all done by the workflow below.

## YAML workflow

Create a file `.github/workflows/refreshVersions.yml` with this content

```YAML
# Worfklow for https://jmfayard.github.io/refreshVersions/

name: RefreshVersions

on:
  workflow_dispatch:
  schedule:
   - cron: '0 7 * * 1'

jobs:
  "Refresh-Versions":
    runs-on: "ubuntu-latest"
    steps:
      - id: step-0
        name: check-out
        uses: actions/checkout@v3
        with:
          ref: main
      - id: step-1
        name: setup-java
        uses: actions/setup-java@v3
        with:
          java-version: 17
          distribution: adopt
      - id: step-2
        name: create-branch
        uses: peterjgrainger/action-create-branch@v2.2.0
        with:
          branch: dependency-update
        env:
          GITHUB_TOKEN: {{ '${{' }} secrets.GITHUB_TOKEN }}
      - id: step-3
        name: gradle refreshVersions
        uses: gradle/gradle-build-action@v2
        with:
          arguments: refreshVersions
      - id: step-4
        name: Commit
        uses: EndBug/add-and-commit@v9
        with:
          author_name: GitHub Actions
          author_email: noreply@github.com
          message: Refresh versions.properties
          new_branch: dependency-update
          push: --force --set-upstream origin dependency-update
      - id: step-5
        name: Pull Request
        uses: repo-sync/pull-request@v2
        with:
          source_branch: dependency-update
          destination_branch: main
          pr_title: Upgrade gradle dependencies
          pr_body: '[refreshVersions](https://github.com/jmfayard/refreshVersions) has found those library updates!'
          pr_draft: true
          github_token: {{ '${{' }} secrets.GITHUB_TOKEN }}
```

Commit and Push.

To test that the workflow works, you can use the `workflow_dispatch` feature.

<img width="1308" alt="Actions_·_TIGNUM_backend-tignum-x" src="https://user-images.githubusercontent.com/459464/182308538-42ad3750-49a2-43ae-b39e-2dcb508ef974.png">


## Kotlin Worfklow

Want to customize the workflow?

[YAML Programming sucks](https://dev.to/jmfayard/github-actions-a-new-hope-in-yaml-wasteland-1i9c) so you should consider
modifying instead the Kotlin script powered by [github-actions.kts](https://krzema12.github.io/github-actions-kotlin-dsl/)
with which the YAML above was generated.

- Create a file `.github/workflows/refreshVersions.main.kts`
- Make it executable with `chmod +x .github/workflows/refreshVersions.main.kts`
- Put it the content above
- Run with ` .github/workflows/refreshVersions.main.kts`

```kotlin
#!/usr/bin/env kotlin
// Usage: $ .github/workflows/refreshVersions.main.kts

@file:DependsOn("it.krzeminski:github-actions-kotlin-dsl:0.23.0")
// Find latest version at https://github.com/krzema12/github-actions-kotlin-dsl/releases

import it.krzeminski.githubactions.actions.actions.CheckoutV3
import it.krzeminski.githubactions.actions.actions.SetupJavaV3
import it.krzeminski.githubactions.actions.endbug.AddAndCommitV9
import it.krzeminski.githubactions.actions.gradle.GradleBuildActionV2
import it.krzeminski.githubactions.actions.peterjgrainger.ActionCreateBranchV2
import it.krzeminski.githubactions.actions.reposync.PullRequestV2
import it.krzeminski.githubactions.domain.RunnerType
import it.krzeminski.githubactions.domain.Workflow
import it.krzeminski.githubactions.domain.triggers.Cron
import it.krzeminski.githubactions.domain.triggers.Schedule
import it.krzeminski.githubactions.domain.triggers.WorkflowDispatch
import it.krzeminski.githubactions.dsl.expressions.expr
import it.krzeminski.githubactions.dsl.workflow
import it.krzeminski.githubactions.yaml.writeToFile
import java.nio.file.Paths

private val everyMondayAt7am = Cron(minute = "0", hour = "7", dayWeek = "1")

val branch = "dependency-update"
val commitMessage = "Refresh versions.properties"
val prTitle = "Upgrade gradle dependencies"
val prBody = "[refreshVersions](https://github.com/jmfayard/refreshVersions) has found those library updates!"
val javaSetup = SetupJavaV3(
    javaVersion = "17",
    distribution = SetupJavaV3.Distribution.Adopt,
)

val workflowRefreshVersions: Workflow = workflow(
    name = "RefreshVersions",
    on = listOf(
        Schedule(listOf(everyMondayAt7am)),
        WorkflowDispatch(),
    ),
    sourceFile = Paths.get(".github/workflows/refreshversions.main.kts"),
) {
    job(
        id = "Refresh-Versions",
        runsOn = RunnerType.UbuntuLatest,
    ) {
        uses(
            name = "check-out",
            action = CheckoutV3(ref = "main"),
        )
        uses(
            name = "setup-java",
            action = javaSetup,
        )
        uses(
            name = "create-branch",
            action = ActionCreateBranchV2(branch),
            env = linkedMapOf(
                "GITHUB_TOKEN" to expr { secrets.GITHUB_TOKEN },
            ),
        )
        uses(
            name = "gradle refreshVersions",
            action = GradleBuildActionV2(arguments = "refreshVersions"),
        )
        uses(
            name = "Commit",
            action = AddAndCommitV9(
                authorName = "GitHub Actions",
                authorEmail = "noreply@github.com",
                message = commitMessage,
                newBranch = branch,
                push = "--force --set-upstream origin dependency-update",
            ),
        )
        uses(
            name = "Pull Request",
            action = PullRequestV2(
                sourceBranch = branch,
                destinationBranch = "main",
                prTitle = prTitle,
                prBody = prBody,
                prDraft = true,
                githubToken = expr { secrets.GITHUB_TOKEN },
            ),
        )
    }

}
println("Updating ${workflowRefreshVersions.targetFileName}")
workflowRefreshVersions.writeToFile()

```
