import de.fayard.refreshVersions.core.FeatureFlag.*

pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
        maven(url = "https://dl.bintray.com/jmfayard/maven")
    }

    val versionFile = rootDir.parentFile.resolve("plugins/version.txt")
    val pluginsVersion = versionFile.readLines().first()

    @Suppress("UnstableApiUsage")
    plugins {
        id("de.fayard.refreshVersions").version(pluginsVersion)
    }
}

plugins {
    id("com.gradle.enterprise").version("3.1.1")
    id("de.fayard.refreshVersions")
}

refreshVersions {

    /**  ./gradlew refreshVersions by default
    refrehVersions: bloc guarded by flag=FOO_EXPERIMENTAL should not run
    refrehVersions: bloc guarded by flag=FOO_OKISH should not run
    refrehVersions: bloc guarded by flag=FOO_STABLE should run
    refrehVersions: bloc guarded by flag=FOO_DELETED should not run
     **/


    featureFlags {
        enable(FOO_EXPERIMENTAL)
        disable(FOO_OKISH)
    }
    /**
     * $ ./gradlew refreshVersions --enable FOO_EXPERIMENTAL
     * refrehVersions: bloc guarded by flag=FOO_EXPERIMENTAL should run
     */

    /**
     * $ ./gradlew refreshVersions --disable FOO_OKISH
     * refrehVersions: bloc guarded by flag=FOO_OKISH should not run
     */

    //enable(FOO_DELETED, FOO_EXPERIMENTAL, FOO_OKISH, FOO_STABLE)
    /** if configuration above is uncommented
    refrehVersions: bloc guarded by flag=FOO_EXPERIMENTAL should run
    refrehVersions: bloc guarded by flag=FOO_OKISH should run
    refrehVersions: bloc guarded by flag=FOO_STABLE should run
    refrehVersions: bloc guarded by flag=FOO_DELETED should not run
     */

    //disable(FOO_DELETED, FOO_EXPERIMENTAL, FOO_OKISH, FOO_STABLE)
    /**
     * if configuration above is uncommented
    refrehVersions: bloc guarded by flag=FOO_EXPERIMENTAL should not run
    refrehVersions: bloc guarded by flag=FOO_OKISH should not run
    refrehVersions: bloc guarded by flag=FOO_STABLE should run
    refrehVersions: bloc guarded by flag=FOO_DELETED should not run
     */

    extraArtifactVersionKeyRules(file("refreshVersions-extra-rules.txt"))
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}

rootProject.name = "sample-kotlin"
