package jmfayard.github.io

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class SyncLibsTask : DefaultTask() {

    init {
        description = "Update buildSrc/src/main/java/Libs.kt"
        group = "build"
    }

    var jsonInputPath = "build/dependencyUpdates/report.json"


    val outputDir = project.file("buildSrc/src/main/java")


    @TaskAction
    fun taskAction() {

        val jsonInput = project.file(jsonInputPath)

        val outputFile = outputDir.resolve("$LibsClassName.kt")

        val fileExisted = outputFile.canRead()

        println(helpMessageBefore(jsonInput))

        createBasicStructureIfNeeded()


        val json = jsonInput.readText()
        val moshiAdapter: JsonAdapter<DependencyGraph> = Moshi.Builder().build().adapter(DependencyGraph::class.java)
        val dependencyGraph: DependencyGraph = moshiAdapter.fromJson(json)!!
        val dependencies: List<Dependency> = parseGraph(dependencyGraph)

        val kotlinPoetry: KotlinPoetry = kotlinpoet(dependencies, dependencyGraph.gradle)
        kotlinPoetry.Libs.writeTo(outputDir)
        kotlinPoetry.Versions.writeTo(outputDir)

        println(helpMessageAfter(fileExisted, dependencies, outputFile))
    }


    fun createBasicStructureIfNeeded() {
        val folder = project.file("buildSrc/main/java")
        if (folder.isDirectory.not()) {
            folder.mkdirs()
        }
        val buildSrc = project.file("buildSrc/build.gradle.kts")
        if (buildSrc.exists().not()) {
            buildSrc.writeText(INITIAL_BUILD_GRADLE_KTS)
        }
    }


}


