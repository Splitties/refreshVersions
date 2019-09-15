## Contributing

Thank you for considering a contribution! 

This guide explains how to:

- maximize the chance of your changes being accepted
- find the documentation to work on a Gradle plugin
- work on the code base and test your changes

### Before you start

- Check out the parent plugin: [ben-manes/gradle-versions-plugin](https://github.com/ben-manes/gradle-versions-plugin). Your feature request or bug report may be better adressed at this level.
- Check out the [existing issues](https://github.com/jmfayard/buildSrcVersions/issues). 
- Explain your use case and start the discussion before your submit a pull-request


## Writing a Gradle plugin

If you have never worked on a Gradle plugin before, have a glimpse at the following guides

- [Designing Gradle plugins](https://guides.gradle.org/designing-gradle-plugins/)
- [Implementing Gradle plugins](https://guides.gradle.org/implementing-gradle-plugins/)
- [Testing Gradle plugins](https://guides.gradle.org/testing-gradle-plugins/)

https://github.com/gradle-guides/gradle-site-plugin is a wonderful Gradle plugin sample demonstrating established techniques and practices for plugin development as described in the following guides:
                                                                     

### Workflow for this project
   
Start IntelliJ IDEA 2018.3+  

Open in IntelliJ the project from the folder `composite` (composite/build.gradle). 

From the `Gradle` tool window, run the tasks `checkAll` 

![image](https://user-images.githubusercontent.com/459464/51464714-8662c380-1d66-11e9-87f7-2ac45d4ff620.png)


## Tasks 

The `composite` module defines a number of custom tasks to simplify the workflow

```
$ pwd
/Users/jmfayard/try/buildSrcVersions/composite   

$ ./gradlew tasks --group=Custom
Custom tasks
------------
checkAll - Run all checks
hello - Minimal task that do nothing. Useful to debug a failing build
pluginTests - Run plugin unit tests
publishLocally - Publish the plugin locally
publishPlugins - Publishes this plugin to the Gradle Plugin portal.
updateGradle - Update Gradle in all modules
```


### Publishing the plugin locally

The samples are useful to test quickly how the plugin behaves, but sometimes it's best to test it into a real project. 

This is as easy as editing your `Settings`:

```
// MY_PROJECT/settings.gradle(.kts)
pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}
//rootProject.name = "MY_PROJECT"
```

The workflow is then:

- change something inside the plugin
- Run either the task `:publishLocally` from `composite`
- ... or the task `:publishToMavenLocal` from `plugin`
- Then run `:refreshVersions` or `:buildSrcVersions` inside `MY_PROJECT`
