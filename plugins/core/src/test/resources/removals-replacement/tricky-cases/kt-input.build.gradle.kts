plugins {
    kotlin("jvm")
}

dependencies {
    implementation(SomeGroup.subGroup)
    implementation(SomeGroup.subGroup.withoutVersion())
    implementation(SomeGroup.subGroup.artifact1)
    implementation(SomeGroup.subGroup.artifact1.artifact3)
    implementation(SomeGroup.subGroup.artifact2)
    implementation(Ktor.features.gson)
}
