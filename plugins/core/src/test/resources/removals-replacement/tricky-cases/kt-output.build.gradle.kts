plugins {
    kotlin("jvm")
}

dependencies {
    implementation(SomeGroup.anotherGroup)
    implementation(SomeGroup.anotherGroup.withoutVersion())
    implementation(SomeGroup.subGroup.artifact1)
    implementation(SomeGroup.subGroup.artifact1.artifact3)
    implementation(SomeGroup.subGroup.artifact2)
    // TODO: Migrate to Ktor 2. See migration guide here: https://ktor.io/docs/migrating-2.html
    // TODO: Move to the 2 artifacts below (currently commented-out).
    implementation("io.ktor:ktor-gson:_")
//           moved:Ktor.serialization.gson)
//    implementation(Ktor.server.contentNegotiation)
}
