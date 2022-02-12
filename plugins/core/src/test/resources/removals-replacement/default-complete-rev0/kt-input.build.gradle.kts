import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.implementation

plugins {
    kotlin("jvm")
}

dependencies {
    implementation(AndroidX.wear.watchFace.client)
    implementation("AndroidX.wear.watchFace.client")
    implementation(SomeGroup.something)
    implementation(SomeGroup.one)
    implementation ( SomeGroup.two )
    implementation (" SomeGroup.two ")
    implementation(SomeGroup.three)
}
