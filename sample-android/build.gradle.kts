group = "de.fayard"

buildscript {

  repositories {
    mavenCentral()
    google()
  }

  dependencies {
    classpath(Android.tools.build.gradlePlugin)
    classpath(Kotlin.gradlePlugin)
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
