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


Wit this setup:

- You can work at the same time on the `plugin` codebase as well as on the samples `sample-groovy` and `sample-kotlin`.
- Thanks to [Gradle's composite build feature](https://github.com/jmfayard/buildSrcVersions/issues/31), any change you make to the `plugin` codebase is immediatly available to the samples.
- `:checkAll` will run the task `:buildSrcVersions` in both samples.
- `:checkAll` will run the unit tests presents in [plugin/src/test/resources/libs](https://github.com/jmfayard/buildSrcVersions/tree/master/plugin/src/test/kotlin/de/fayard)
