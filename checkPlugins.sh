#!/usr/bin/env bash

cd plugins
./gradlew test publishToMavenLocal

cd ../sample-plugins
./gradlew check refreshVersions

cd ../sample-kotlin
./gradlew check refreshVersions

cd ..sample-groovy
./gradlew check refreshVersions

