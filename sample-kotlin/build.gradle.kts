import com.github.benmanes.gradle.versions.updates.resolutionstrategy.ComponentSelectionWithCurrent
import de.fayard.PluginConfig
import de.fayard.PluginConfig.isNonStable
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
plugins {
    kotlin("jvm") version "1.3.11"
    id("de.fayard.buildSrcVersions") version "0.5.0"
}

group = "de.fayard"

repositories {
    maven {
        setUrl("../plugin/src/test/resources/maven")
    }
}

dependencies {
    implementation("com.google.guava:guava:15.0")
    implementation("com.google.inject:guice:2.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
tasks.withType<Wrapper> {
    gradleVersion = System.getenv("GRADLE_VERSION") ?: "5.6.1"
    distributionType = Wrapper.DistributionType.ALL
}

buildSrcVersions {
    useFdqnFor = mutableListOf()
    renameLibs = "Libs"
    renameVersions = "Versions"
    indent = "  "
    rejectVersionIf { selection: ComponentSelectionWithCurrent ->
        isNonStable(selection.candidate.version) && isNonStable(selection.currentVersion).not()
    }
}
