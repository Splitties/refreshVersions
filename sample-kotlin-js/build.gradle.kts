plugins {
    kotlin("multiplatform")
}

group = "de.fayard"

repositories {
    mavenCentral()
}

kotlin {
    js {
        browser {
            testTask {
                useMocha()
            }
        }
        useCommonJs()
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(Kotlin.stdlib)
            }
        }
        commonTest {
            dependencies {
                implementation(Kotlin.test.common)
                implementation(Kotlin.test.annotationsCommon)
            }
        }

        getByName("jsMain") {
            dependencies {
                implementation(npm("decamelize", "_", generateExternals = true))

                implementation(npm("react", "_"))
                implementation(npm("moment", "_"))

                implementation(npm("@googlemaps/js-api-loader", "_"))
            }
        }

        getByName("jsTest") {
            dependencies {
                implementation(Kotlin.test.js)
                implementation(Testing.Kotest.assertions.core)
            }
        }
    }
}
tasks.wrapper {
    val versionFile = rootDir.parentFile.resolve("plugins/gradle-version.txt")
    gradleVersion = versionFile.readLines().first()
}
