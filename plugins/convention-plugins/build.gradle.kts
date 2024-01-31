plugins {
    `kotlin-dsl`
}

fun plugin(id: String, version: String) = "$id:$id.gradle.plugin:$version"

dependencies {
    implementation(plugin(id = "com.gradle.plugin-publish", version = "_"))
}
