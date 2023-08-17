# Submitting dependency notations

We want to provide dependency notations for more popular libraries.

Want to contribute some?

Here is what a dependency notation should look like:

```kotlin
@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused") // 1

import de.fayard.refreshVersions.core.DependencyGroup
import org.gradle.kotlin.dsl.IsNotADependency

/**                                                             // 2
 * painless Kotlin dependency injection
 *
 * - [Official website here](https://kodein.org/di/)
 * - GitHub page: [Kodein-Framework/Kodein-DI](https://github.com/Kodein-Framework/Kodein-DI)
 * - [GitHub Releases here](https://github.com/Kodein-Framework/Kodein-DI/releases)
 */
object Kodein : IsNotADependency {                              // 3

    val di = DI                                                 // 4

    object DI : DependencyGroup(                                // 5
        group = "org.kodein.di",                                // 6
        usePlatformConstraints = false,                         // 7
        rawRule = """
            org.kodein.di:kodein-di(-*)
                ^^^^^^^^^
        """.trimIndent()                                        // 8
    ) {
        val bom = module("kodein-bom", isBom = true)            // 9
        val js = module("kodein-di-js")                         // 10
        val androidx = module("kodein-di-framework-android-x")
    }
}
```

What you need to know:

1. We use no package on purpose - and suppress the corresponding warning - so that users don't have to import the dependency notation.
2. We provide a KDoc with a description of what the library does, a link to GitHub, and a link to the documentation. Please, keep the exact same style for consistency.
3. We tag the objects with `IsNotADependency`, so that the IDE shows an error if the user tries to do `implementation(Kodein)`.
4. We support Gradle build scripts in both Kotlin and Groovy. `Kodein.DI` works in Kotlin, but not in Groovy. That's why we have the property `Kotlin.di`, which works in both.
5. We use the base class `DependencyGroup` to define a group of dependency notations. Note that this class also has the `IsNotADependency` marker interface. If you want the object or sub-group to be a dependency notation itself, use the `DependencyNotationAndGroup` class instead.
6. The maven group that will be used for all the modules of this `DependencyGroup` (or `DependencyNotationAndGroup`).
7. If the library provides a Bill of Materials (BoM for short), or another kind of platform constraints that should always be used, we set `usePlatformConstraints = true`. (As you can see, in the case of the example, it's not made mandatory.)
8. All dependency notations with a name like `org.kodein.di:kodein-di(-*)` will use the same version `version.kodein.di` because we defined an artifact rule. To learn more about [those rules, have a look here](https://github.com/jmfayard/refreshVersions/tree/main/plugins/dependencies/src/main/resources/refreshVersions-rules).
9. `DependencyGroup` and `DependencyNotationAndGroup` have first class support for BoMs via the `isBom = true` parameter. It sets the boolean `usePlatformConstraints` to `true` for the group and does various checks.
10. A module is defined via the `= module("module.name")` syntax.

Three more things before you start coding:

- Look at [`Square`, `COIL`, `Http4K`, `Spring`, `Kodein`, and others](https://github.com/jmfayard/refreshVersions/tree/main/plugins/dependencies/src/main/kotlin/dependencies) for inspiration.
- **Try to not forget any artifact!**. One of the best ways to do that is to open an issue in the project of the library for which you contribute dependency notations.
- **Run the unit tests!** There are multiple checks that are done to prevent the most common mistakes.
