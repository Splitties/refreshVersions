# Contributing guidelines

Thank you for considering a contribution! 

This guide explains how to:

- maximize the chance of your changes being accepted
- find the documentation to work on a Gradle plugin
- work on the code base and test your changes

## Submitting issues

TK

## Submitting PRs

### Before you start

#### Let us know about your upcoming contribution

TK

### Setting up the project in your IDE

#### Get the right IDE

We recommend using the most recent version of IntelliJ IDEA to contribute to this project (consider 2020.2 or newer).

#### Prepare your fork

1. Fork this project from GitHub if not already done.
2. Clone your fork locally.
3. Checkout the `develop` branch.
4. If your fork is not fresh, ensure its `develop` branch is up to date with upstream:
    1. Run the `git fetch upstream` command.
    2. Run the `git merge upstream/develop` command.
5. Create a new branch based on `develop` with a name describing what it is about.

#### Import the "plugins" Gradle project

1. Locate the `plugins/settings.gradle.kts` file in the "Project" tool window.
2. Right-click on it to open the contextual menu.
3. Select "Import Gradle Project".

#### Publish the current version locally

This step is required for the samples to work afterwards and enable you to test your changes.

1. Ensure the version in the `plugins/version.txt` file ends with `-SNAPSHOT` to avoid overlapping a published version.
2. In the "Gradle" tool window, or from the terminal, run the `publishToAppropriateRepo` task.

#### Import the samples to test the changes

You can do the same for the projects you to test your changes:

### Testing changes locally

#### Publishing

TK

#### Using the local version in your project

The samples are useful to test quickly how the plugin behaves, but sometimes it's best to test it into a real project.

TK

## Contributing specific parts

TK

## Extra resources

### Writing a Gradle plugin

If you have never worked on a Gradle plugin before, have a glimpse at the following guides

- [Designing Gradle plugins](https://guides.gradle.org/designing-gradle-plugins/)
- [Implementing Gradle plugins](https://guides.gradle.org/implementing-gradle-plugins/)
- [Testing Gradle plugins](https://guides.gradle.org/testing-gradle-plugins/)
