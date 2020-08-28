import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
    id("org.jetbrains.kotlin.android.extensions")
    id("gradle.site")
}

apply(from = "android.gradle")

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation(platform(Firebase.bom))
    implementation(Firebase.authentication)
    implementation("com.google.firebase:firebase-analytics")


    implementation(Kotlin.stdlib.jdk7)
    implementation(AndroidX.appCompat)
    implementation(AndroidX.constraintLayout)
    implementation(AndroidX.lifecycle.runtime)
    implementation(AndroidX.lifecycle.viewModel)
    kapt(AndroidX.lifecycle.compiler)
    testImplementation(Testing.junit4)
    androidTestImplementation(AndroidX.test.core)
    androidTestImplementation(AndroidX.archCore.testing)
    androidTestImplementation(Testing.mockito.core)
    androidTestImplementation(Testing.mockito.android)
    androidTestImplementation(Testing.mockito.kotlin)
    androidTestImplementation(AndroidX.test.runner)
    androidTestImplementation(AndroidX.test.rules)
    androidTestImplementation(AndroidX.test.espresso.core)
}
