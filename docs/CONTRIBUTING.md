# Contributing guidelines

Thank you for considering a contribution!

This guide explains how to:

- maximize the chance of your changes being accepted
- find the documentation to work on a Gradle plugin
- work on the code base and test your changes

## We discuss before we code

Please don't spend hours implementing a feature and then submit a pull-request that we may not be able to accept.

Instead [start a discussion](https://github.com/jmfayard/refreshVersions/discussions) to gather early feedback from us. That will save both your time and ours.

## We report issues properly

TK: use the playground or the samples instead of your private project

TK: give us a build scan URL

## We have a code of conduct

Whenever you are about to post or commit, ask yourself "Would an idiot do that?"

And if they would, do not do that thing.

https://youtu.be/KFwUcEwD4l4

[![](http://www.theofficequotes.com/screenshots/84521e6a7a4ea0cf2057070b8fa200ba.jpg)](https://youtu.be/KFwUcEwD4l4)
___

Q: What about breathing?

A: The behavior must be exclusive to idiocy.


## How to open the project in IntelliJ IDEA

We recommend using the most recent version of IntelliJ IDEA to contribute to this project (consider 2020.2 or newer).

Import the "plugins" Gradle project

1. Locate the `plugins/settings.gradle.kts` file in the "Project" tool window.
2. Right-click on it to open the contextual menu.
3. Select "Import Gradle Project".

TK: the fix-ide.sh script

## We use feature flags to avoid breaking other people's build

TK

## We publish the plugin locally with the task `:publishToMavenLocal`

This step is required for the samples to work afterwards and enable you to test your changes.

1. Checks that the `plugins/version.txt` file contains a version ending with `-LOCAL-SNAPSHOT` to avoid overlapping a published version.
2. In the "Gradle" tool window, or from the terminal, run the `publishToMavenLocal` task.

## We test that the samples are still working with `checkSamples.sh`

TK

## We are mindful with the plugin dependencies

TK

## We write tests

TK

## How we test the plugin in other projects

The samples are useful to test quickly how the plugin behaves, but sometimes it's best to test it into a real project.

TK

## How we publish a non-stable version of refreshVersions

TK jitpack

TK dev version

## How we contribute new dependency notations

TK we don't at the moment

## How we update the documentation

TK mkdocs serve etc..

TK https://jmfayard.github.io/rvdev/

## How to develop a Gradle plugin

If you have never worked on a Gradle plugin before, have a glimpse at the following guides

- [Designing Gradle plugins](https://guides.gradle.org/designing-gradle-plugins/)
- [Implementing Gradle plugins](https://guides.gradle.org/implementing-gradle-plugins/)
- [Testing Gradle plugins](https://guides.gradle.org/testing-gradle-plugins/)

## Our checklist before merging a pull-request

TK make a WIP pull request for early feedback, or ask for a detailed review if you are almost done

TK merge main

TK delete branch

TK ask for a new review
