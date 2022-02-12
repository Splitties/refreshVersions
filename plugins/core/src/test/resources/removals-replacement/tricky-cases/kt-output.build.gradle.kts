plugins {
    kotlin("jvm")
}

dependencies {
    implementation(SomeGroup.anotherGroup)
    implementation(SomeGroup.anotherGroup.withoutVersion())
    implementation(SomeGroup.subGroup.artifact1)
    implementation(SomeGroup.subGroup.artifact1.artifact3)
    implementation(SomeGroup.subGroup.artifact2)
}
