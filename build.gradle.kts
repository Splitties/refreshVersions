plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `maven-publish`
    id("com.gradle.plugin-publish") version "0.10.0"

}

group = "jmfayard.github.io"
version = "0.2.3"

gradlePlugin {
    plugins {
        register("gradle-kotlin-dsl-libs") {
            id = "jmfayard.github.io.gradle-kotlin-dsl-libs"
            displayName = "gradle-kotlin-dsl-libs"
            description = "Painless dependencies management with the gradle kotlin-dsl"
            implementationClass = "jmfayard.github.io.GradleKotlinDslLibsPlugin"
        }
    }
}

publishing {
    repositories {
        maven(url = "build/repository")
    }
}

repositories {
    jcenter()
}

pluginBundle {
    website = "https://github.com/jmfayard/gradle-kotlin-dsl-libs"
    vcsUrl = "https://github.com/jmfayard/gradle-kotlin-dsl-libs"
    tags = listOf("kotlin", "kotlin-dsl", "versioning")
}
dependencies {
    compile("com.github.ben-manes:gradle-versions-plugin:0.20.0")
    testImplementation("org.amshove.kluent:kluent:1.4")
    testImplementation("org.testng:testng:6.14.3")

    compile("com.squareup.okio:okio:2.1.0")
    compile( "com.squareup.moshi:moshi:1.7.0")
    compile("com.squareup:kotlinpoet:1.0.0-RC1")

}
