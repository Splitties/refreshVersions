import java.net.URI

plugins {
    `java-library`
    `maven-publish`
}

group = "com.example"
version = "0.1.0-alpha03"

publishing {
    repositories {
        maven {
            name = "GoogleCloudStorage"
            url = URI("gcs://refreshversions-testing/maven")
        }
    }
    publications {
        create<MavenPublication>("library") {
            from(components["java"])
        }
    }
}
