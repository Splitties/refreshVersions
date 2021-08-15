# Submitting dependency notations

We want to provide dependency notations for more popular libraries.

Want to contribute some?

Here is what a dependency notation should look like:

```kotlin
@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused") // 1

import de.fayard.refreshVersions.core.DependencyGroup
import org.gradle.api.Incubating
import org.gradle.kotlin.dsl.IsNotADependency

/**                                                             // 2
 * painless Kotlin dependency injection
 *
 * - [Official website here](https://kodein.org/di/)
 * - GitHub page: [Kodein-Framework/Kodein-DI](https://github.com/Kodein-Framework/Kodein-DI)
 * - [GitHub Releases here](https://github.com/Kodein-Framework/Kodein-DI/releases)
 */
@Incubating
object Kodein: IsNotADependency {                               // 3

    val di = DI                                                 // 4

    object DI : DependencyGroup(                                // 5
        group = "org.kodein.di",                                // 6
        usePlatformConstraints = true,                          // 7
        rawRule = """
            org.kodein.di:kodein-di(-*)
                ^^^^^^^^^
        """.trimIndent()                                        // 8
    ) {
        val bom = module("kodein-bom", isBom = true)           // 9
        val js = module("kodein-di-js")                        // 10
        val androidx = module("kodein-di-framework-android-x")
    }
}
```

Here is what you need to know:

1. We use on purpose no package - and suppress the corresponding warning - so that the user doesn't have to import the dependency notation
2. We provide a KDoc with a description of what the library does and link to GitHub and the documentation
3. We tag classes with `IsNotADependency` so that the IDE shows a warning if the user tries to do `implementation(Kodein)`
4. We support Gradle build scripts in both Kotlin and Groovy. `Kodein.DI` works in Kotlin but not in Groovy. That's why we have the property `Kotlin.di` who works in both.
5. We use the base class `DependencyGroup` to define a group of dependency notations.
6. The maven group that will be used for all the modules of this `DependencyGroup`.
7. If the library provides a Bill of Materials `BoM` of another kind of platform constraints, we set `usePlatformConstraints = true`.
8. All dependency notations with a name like `org.kodein.di:kodein-di(-*)` will use the same version `version.kodein.di` because we defined an artifact rule. To learn more about [refreshVersions rules, have a look here](thttps://github.com/jmfayard/refreshVersions)
9. DependencyGroup has first class support for `BoM`s via the `isBom = true` parameter. It switches the boolean `usePlatformConstraints = true` and does various checks.
10. A module is defined via the `= module("module.name")` syntax.

Three more things before you  start coding:

- Look at [`Http4K`, `Spring` or `Kodein`](https://github.com/jmfayard/refreshVersions/tree/main/plugins/dependencies/src/main/kotlin/dependencies) for inspiration.
- **Try to not forget any artifact!**. One of the best ways to do that is to open an issue in the project of the library for which you contribute dependency notations.
- **Run the unit tests!**. There are multiple checks that are done to prevent the most common mistakes.
