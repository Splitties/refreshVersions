# Migration

You can migrate existing dependencies, so they use the version placeholder and can have their available updates found by refreshVersions and easily applied.

We provide an **experimental** interactive Gradle task made for migration.

To use it, run the following command and **follow the instructions**:
```shell
./gradlew migrateToRefreshVersionsDependenciesConstants --console=plain
```

## About the experimental status

That migration task is **safe to use** (except if you have different modules with different versions of the same dependency family), but its UX has a few known issues. Here's the improvement plan:

1. Ergonomics (Don't require to type a full word to validate migration of a single dependency)
2. Keep track of what has been migrated already to not show out of sync hardcoded dependencies count
3. Automatically replace dependency notations in the build files when possible safely
4. Have better rules to ignore dependencies added by plugins (e.g. "kotlin-android-extensions" plugin)
5. Its name (remove lengthy `migrateToRefreshVersionsDependenciesConstants ` and have a `--migrate` flag to `refreshVersions` redirect to it instead)

These improvements are planned for the 1.0 release.

You can follow [this issue]({{link.issues}}/169) to track when it is resolved exactly, and you can also vote for it with a 👍.
