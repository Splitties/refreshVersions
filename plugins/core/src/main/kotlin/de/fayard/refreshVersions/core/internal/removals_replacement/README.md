# Dependency notations replacements and removals

## What

This package contains code that can be run before the build files of the host Gradle project are compiled and evaluated (except for `settings.gradle[.kts]`, but it's not a concern since dependency notations can't be used there anyway).

The responsibility of that code is to **replace any removed built-in dependency notation** with the shortest replacement, if available, or with the "hardcoded" dependency notations.

It might also **add comments to provide actionable information in context** (since it'll usually be about deprecations).

## Why

This allows refreshVersions to get rid of deprecated dependency notations, and repurpose old dependency notation symbols for new coordinates, without changing the dependencies of the host projects itself.
