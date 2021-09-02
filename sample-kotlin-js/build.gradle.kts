plugins {
    kotlin("multiplatform")
}

group = "de.fayard"

repositories {
    mavenLocal()
    mavenCentral()
}

kotlin {
    js() {
        browser {
            testTask {
                useMocha()
            }
        }
        useCommonJs()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Kotlin.stdlib)
            }
        }

        val commonTest by getting {
            dependencies {
                implementation(Kotlin.test.common)
                implementation(Kotlin.test.annotationsCommon)
            }
        }

        val jsMain by getting {
            dependencies {
                implementation(npm("decamelize", "_", generateExternals = true))

                implementation(npm("react", "_"))
                implementation(npm("moment", "_"))

                implementation(npm("@googlemaps/js-api-loader", "_"))
            }
        }

        val jsTest by getting {
            dependencies {
                implementation(Kotlin.test.js)
                implementation(Testing.Kotest.assertions.core)
            }
        }
    }
}
