plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `maven-publish`
    id("com.gradle.plugin-publish") version "0.10.0"

}

group = "jmfayard.github.io"
version = "0.1"

gradlePlugin {
    plugins {
        register("greetingsPlugin") {
            id = "jmfayard.github.io.gradle-kotlin-dsl-libs"
            displayName = "gradle-kotlin-dsl-libs"
            description = "Painless dependencies management with the gradle kotlin-dsl"
            implementationClass = "io.github.jmfayard.GradleKotlinDslLibsPlugin"
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
