package de.fayard.refreshVersions

import java.io.File

val pluginsVersion = File(".").absoluteFile.parentFile.parentFile.resolve("version.txt").useLines {
    it.first()
}
