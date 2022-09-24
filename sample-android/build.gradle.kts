group = "de.fayard"

buildscript {

  repositories {
    mavenCentral()
    google()
  }

  dependencies {
    classpath("com.android.tools.build:gradle:_")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:_")
  }
}


subprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register<DefaultTask>("hello") {
    group = "Custom"
}

buildScan {
    setTermsOfServiceUrl("https://gradle.com/terms-of-service")
    setTermsOfServiceAgree("yes")
    publishAlways()
}

tasks.wrapper {
    val versionFile = rootDir.parentFile.resolve("plugins/gradle-version.txt")
    gradleVersion = versionFile.readLines().first()
}
