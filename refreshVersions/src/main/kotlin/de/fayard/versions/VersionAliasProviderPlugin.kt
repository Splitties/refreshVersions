package de.fayard.versions

import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class VersionAliasProviderPlugin : Plugin<Project> {

    abstract val artifactVersionKeyRules: List<String>
}
