import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.splitties.gradle.VersionFileWriter
import org.splitties.gradle.putVersionInCode

plugins {
    id("gradle-plugin")
    `java-test-fixtures`
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        create("refreshVersions-core") {
            id = "de.fayard.refreshVersions-core"
            displayName = "./gradlew refreshVersions"
            description = "Painless dependencies management"
            tags = listOf("dependencies", "versions", "buildSrc", "kotlin", "kotlin-dsl")
            implementationClass = "de.fayard.refreshVersions.core.RefreshVersionsCorePlugin"
        }
    }
}

dependencies {
    compileOnly(gradleKotlinDsl())
    implementation(KotlinX.coroutines.core)
    implementation(Square.okHttp3)
    implementation(Square.okHttp3.loggingInterceptor)
    implementation(Square.retrofit2)!!.apply {
        because("It has ready to use HttpException class")
    }
    implementation(Square.moshi.kotlinReflect)

    testImplementation(Square.okHttp3.loggingInterceptor)
    testImplementation(platform(notation = Testing.junit.bom))
    testImplementation(Testing.junit.jupiter)
    testImplementation(Testing.kotest.runner.junit5)
    testImplementation(Kotlin.test.annotationsCommon)
    testImplementation(Kotlin.test.junit5)

    testFixturesApi(Square.okHttp3)
    testFixturesApi(Square.okHttp3.loggingInterceptor)
    testFixturesApi(KotlinX.coroutines.core)
    testFixturesApi(Kotlin.test.annotationsCommon)
    testFixturesApi(Kotlin.test.junit5)
}

kotlin {
    target.compilations.let {
        it.getByName("testFixtures").associateWith(it.getByName("main"))
    }
}

(components["java"] as AdhocComponentWithVariants).let { javaComponent ->
    javaComponent.withVariantsFromConfiguration(configurations["testFixturesApiElements"]) { skip() }
    javaComponent.withVariantsFromConfiguration(configurations["testFixturesRuntimeElements"]) { skip() }
}

sourceSets.main {
    kotlin.srcDir("build/gen")
}

putVersionInCode(
    outputDirectory = layout.dir(provider { file("build/gen") }),
    writer = VersionFileWriter.Kotlin(
        fileName = "PluginVersion.kt",
        `package` = "de.fayard.refreshVersions",
        propertyName = "thisProjectVersion"
    )
)

kotlin {
    jvmToolchain(8)
    compilerOptions {
        apiVersion = KotlinVersion.KOTLIN_1_8 // https://docs.gradle.org/current/userguide/compatibility.html#kotlin
        freeCompilerArgs.add("-opt-in=de.fayard.refreshVersions.core.internal.InternalRefreshVersionsApi")
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
