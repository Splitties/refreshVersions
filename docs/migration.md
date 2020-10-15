# Migration

There is currently a chicken and egg problem while migrating an existing project to refreshVersions:

- your versions are hard-coded in `build.gradle` so gradle refreshVersion does not maange them
- but if you replace them with the placeholder `_`, gradle refreshVersions will use the **latest** version of your library instead of the one your project was using.

This will be resolved, please follow the issue [#268 How to migrate an existing project to refreshVersions]({{link.issues}}/268)
