# Gradle refreshVersions
[![]({{badge.refreshVersions}})]({{link.gradlePluginPortal}}) [![]({{badge.slack}})]({{link.slack}}) [![]({{badge.pr}})]({{link.github}}/pulls) [![]({{badge.mit}})]({{link.master}}/LICENSE.txt)

> Life is too short to google for dependencies and versions

[![](img/screencast.png)](http://www.youtube.com/watch?v=VhYERonB8co "Gradle refreshVersions")


## Why refreshVersions?

Gradle multi-module projects are on the rise.

At the same time, library vendors publish very modularized artifacts. On Android for example, the big fat support libraries are dead, while `AndroidX`
contains more than 200 different dependencies, grouped in 70 group of dependencies, each having its own version.

Both trends come with great technical benefits.

However, they also make it much more tedious to find where to add and update your dependencies in all those `build.gradle[.kts]` files!

Can we have our cake and eat it?

We firmly believe that yes, **we can have the benefits of modularization without the hell of dependency management**.

So what does `gradle refreshVersions`?

## Centralize your dependencies in a proper file format

![](img/versions.properties.png)

Everyone who uses Gradle in a multi-modules environment
has looked for a way to centralize all dependencies in one place.
And there are solutions for that.
Too many solutions in fact.
Some put their versions in a variable `val retrofitVersion = 2.9.0`, others in `rootproject.ext`,
others in a `libraries.gradle` file, others still in `buildSrc/src/main/Versions.kt`,
and the list goes on and on!

A problem common to all those solutions is that they store the versions
**in a turing-complete programming language** (Groovy or Kotlin).
Then people try to hack together regular expressions to read or modify those files.

We think this is wrong!

Instead, **gradle refreshVersions** stores all the versions in one place in a proper file format:
Java Properties, like [`gradle.properties`](https://dev.to/jmfayard/configuring-gradle-with-gradle-properties-211k).
Just like npm's *package.json*, Maven's *pom.xml*, this file can be easily read and written by a computer program.
Yes to better tooling!

## No Magic!

You wonder how this works?

No black magic involved, we leverage Gradle public APIs that are designed to edit declared dependencies (`Configuration.withDependencies`, and `PluginManagement.resolutionStrategy`), and we edit the versions constraints there.
The rest is just a set of conventions.

Consider the example below:

| Dependency notation                           | Version key                                   |
|-----------------------------------------------|-----------------------------------------------|
| org.gradle:gradle-hello-world-plugin:_        | version.org.gradle..gradle-hello-world-plugin |
| com.squareup.retrofit2:retrofit:_             | version.retrofit                              |
| com.squareup.retrofit2:retrofit-adapter-xxx:_ | version.retrofit                              |
| plugin with id "com.squareup.sqldelight"      | plugin.com.squareup.sqldelight                |

- gradle refreshVersions works with **an opt-in mechanism.** It only manages dependencies where the version is set to be a placeholder, more specifically the underscore `_`, which, akin to Kotlin, here means that the version is not used, being instead set in the `versions.properties` file.
- gradle refreshVersions has a system of **[rules]({{link.master}}/plugins/dependencies/src/main/resources/refreshVersions-rules)** that allows to set all Retrofit dependencies with the same version key `version.retrofit` , keeping things DRY.

## Auto-magically look up for updates

With this infrastructure being in place, the major feature of **gradle refreshVersions** is like its name suggests: **It will look up for all available updates of the dependencies used in the project.**

Simply run `$ ./gradlew refreshVersions` and you will see the available updates **as comments**:

![](img/versions.properties_step02.png)

Why as comment? Because while googling manually for available updates is a monkey job that is best done by a computer program, while **deciding whether to upgrade or not is your job** as a programmer.

Read more: [Updating Dependencies]({{link.site}}/updating-dependencies/)

## It's fast!

The Kotlin Libraries Playground contains about 80 dependencies.

How long would it take to search for all available updates?

Well, if you do it manually: *enough to hate your life*.

With the [gradle-versions-plugin](https://github.com/ben-manes/gradle-versions-plugin) it will take about two minutes.

With refreshVersions, you are done in 10 seconds! [See benchmarks here]({{link.playground}}/pull/69)

## Add dependencies quickly!

gradle refreshVersions provides read-to-use organized constants for popular libraries.

![](img/dependencies_constants_autocomplete_2.png)

Read more: [Adding Dependencies](https://jmfayard.github.io/refreshVersions/adding-dependencies/)

## Requirements

- Gradle 6, the more recent version the better
- Groovy or Kotlin DSL
- IntelliJ IDEA & Android Studio


## Try it out with zero setup!

The simplest way to try out gradle refreshVersions is with our official sample,
the [**Kotlin libraries Playground**]({{link.playground}})!

Everything is pre-configured here, so just open the project in IntelliJ and start adding and upgrading dependencies.

[![](img/kotlin-libraries-playground.png)]({{link.playground}})

*Warning: You may also learn a ton of things about Kotlin libraries in the process!*

## Funding ❤️

This project has sponsors that help the maintainers dedicate more time to this project and keep it updated.

Thanks a lot to them! ❤️❤️

[You can see them all here. 👀]({{link.master}}/SPONSORS.md) Feel free to join them in supporting this project! 💪


## Contributing

- This project is under the MIT License.
- Explain your use case and start the discussion before your submit a pull-request
- [CONTRIBUTING]({{link.site}}/CONTRIBUTING) describes the process for submitting pull requests.
