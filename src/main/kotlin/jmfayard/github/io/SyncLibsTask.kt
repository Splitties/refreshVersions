package jmfayard.github.io

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import okio.buffer
import okio.source
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class SyncLibsTask : DefaultTask() {

    init {
        description = "Update buildSrc/src/main/kotlin/Libs.kt"
        group = "build"
    }

    var jsonInputPath = "build/dependencyUpdates/report.json"


    var outputDir = project.file("buildSrc/src/main/kotlin")


    @TaskAction
    fun taskAction() {

        val jsonInput = project.file(jsonInputPath)

        val outputFile = outputDir.resolve("$LibsClassName.kt")

        val fileExisted = outputFile.canRead()

        println(helpMessageBefore(jsonInput))

        createBasicStructureIfNeeded()


        val moshiAdapter: JsonAdapter<DependencyGraph> = Moshi.Builder().build().adapter(DependencyGraph::class.java)
        val dependencyGraph: DependencyGraph = moshiAdapter.fromJson(jsonInput.source().buffer())!!

        val dependencies: List<Dependency> = parseGraph(dependencyGraph)

        val kotlinPoetry: KotlinPoetry = kotlinpoet(dependencies, dependencyGraph.gradle)
        kotlinPoetry.Libs.writeTo(outputDir)
        kotlinPoetry.Versions.writeTo(outputDir)

        println(helpMessageAfter(fileExisted, dependencies, outputFile))
    }


    fun createBasicStructureIfNeeded() {
        if (outputDir.isDirectory.not()) {
            outputDir.mkdirs()
        }
        val buildGradleKts = project.file("buildSrc/build.gradle.kts")
        if (buildGradleKts.exists().not()) {
            println("Creating ${buildGradleKts.absolutePath}")
            buildGradleKts.writeText(INITIAL_BUILD_GRADLE_KTS)
        }

        val settingsGradleKts = project.file("buildSrc/settings.gradle.kts")
        if (settingsGradleKts.exists().not()) {
            println("Creating empty ${settingsGradleKts.absolutePath}")
            settingsGradleKts.writeText("")
        }
    }


}


