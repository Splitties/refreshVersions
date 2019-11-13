Sample project to demo how to manage Gradle dependencies with

- [IntelliJ EAP - Package Search](https://blog.jetbrains.com/idea/2019/10/introducing-package-search-eap/) to add packages
- The Gradle plugin [`de.fayard.refreshVersions`](https://plugins.gradle.org/plugin/de.fayard.refreshVersions) 
- The Gradle plugin [`com.louiscad.splitties`](https://plugins.gradle.org/plugin/com.louiscad.splitties)

## Installation

```bash
$ git clone https://github.com/jmfayard/gradle-refreshVersions-example
```

To get the Package Window and visual interface, you need to install the Package Search plugin that is available on the marketplace. 

If you’re using IntelliJ IDEA 2019.2 or later, you’re already using this functionality when it comes to code completion for packages. 


## Package Search

Package Search provides a nice UX to add a dependency 

![](https://d3nmt5vlzunoa1.cloudfront.net/idea/files/2019/10/Screenshot-2019-10-21-at-10.28.33-1.png)

## gradle refreshVerisons

`./gradlew refreshVersions`  extract dependencies versions to a file [versions.properties](versions.properties)

[Read the friendly documentation](https://github.com/jmfayard/buildSrcVersions/issues/77)

![image](https://user-images.githubusercontent.com/459464/68318855-b1cc5a00-00bd-11ea-827b-cee110839337.png)


## splitties

Splitties contains [typesafe accessor for common dependencies like retrofit, androidx, unit testing, ...](https://github.com/LouisCAD/Splitties/tree/develop/plugin/src/main/kotlin/com/louiscad/splitties)
 
![Screen Shot 2019-11-06 at 5 31 38 PM](https://user-images.githubusercontent.com/459464/68317452-6e70ec00-00bb-11ea-84c4-94d3ef0fb86f.png)


## Should I use it?

No, we are not ready yet.

But please do subscribe to the issue [Long term plan: focus on refreshVersions #104](https://github.com/jmfayard/buildSrcVersions/issues/104)

 
 
