package dependencies

object Testing {

    private const val kotestArtifact = "io.kotestArtifact:kotestArtifact-"

    /***
     * JUnit is a simple framework to write repeatable tests. It is an instance of the xUnit architecture for unit testing frameworks.

     * https://junit.org/junit4/
     */
    const val junit4 = "junit:junit:$placeholderVersion"

    /**
     * The new major version of the programmer-friendly testing framework for Java
     * https://junit.org/junit5/
     */
    const val junitJupiterApi = "org.junit.jupiter:junit-jupiter-api:$placeholderVersion"

    /**
     * The new major version of the programmer-friendly testing framework for Java
     * https://junit.org/junit5/
     */
    const val junitJupiterParams = "org.junit.jupiter:junit-jupiter-params:$placeholderVersion"

    /**
     * The new major version of the programmer-friendly testing framework for Java
     * https://junit.org/junit5/
     */
    const val junitJupiterEnding = "org.junit.jupiter:junit-jupiter-engine:$placeholderVersion"


    /**
     * Powerful, elegant and flexible test framework for Kotlin
     * https://github.com/kotlintest/kotlintest
     * **/
    const val kotlintestRunner = "$kotestArtifact-runner-junit5:$placeholderVersion"
    /**
     * Powerful, elegant and flexible test framework for Kotlin
     * https://github.com/kotlintest/kotlintest
     * **/
    const val kotlintestRunnerConsole = "$kotestArtifact-assertions-runner-console:$placeholderVersion"
    /**
     * Powerful, elegant and flexible test framework for Kotlin
     * https://github.com/kotlintest/kotlintest
     * **/
    const val kotlintestAssertions = "$kotestArtifact-assertions:$placeholderVersion"
    /**
     * Powerful, elegant and flexible test framework for Kotlin
     * https://github.com/kotlintest/kotlintest
     * **/
    const val kotlintestAssertionsArrow = "$kotestArtifact-assertions-arrow:$placeholderVersion"
    /**
     * Powerful, elegant and flexible test framework for Kotlin
     * https://github.com/kotlintest/kotlintest
     * **/
    const val kotlintestExtensions = "$kotestArtifact-assertions-extensions:$placeholderVersion"
    /**
     * Powerful, elegant and flexible test framework for Kotlin
     * https://github.com/kotlintest/kotlintest
     * **/
    const val kotlintestSpring = "$kotestArtifact-assertions-extensions-spring:$placeholderVersion"
    /**
     * Powerful, elegant and flexible test framework for Kotlin
     * https://github.com/kotlintest/kotlintest
     * **/
    const val kotlintestKtor = "$kotestArtifact-assertions-extensions-ktor:$placeholderVersion"
    /**
     * Powerful, elegant and flexible test framework for Kotlin
     * https://github.com/kotlintest/kotlintest
     * **/
    const val kotlintestKoin = "$kotestArtifact-assertions-extensions-koin:$placeholderVersion"
    /**
     * Powerful, elegant and flexible test framework for Kotlin
     * https://github.com/kotlintest/kotlintest
     * **/
    const val kotlintestJson = "$kotestArtifact-assertions-extensions-json:$placeholderVersion"
    /**
     * Powerful, elegant and flexible test framework for Kotlin
     * https://github.com/kotlintest/kotlintest
     * **/
    const val kotlintestSystem = "$kotestArtifact-assertions-extensions-system:$placeholderVersion"
    /**
     * Powerful, elegant and flexible test framework for Kotlin
     * https://github.com/kotlintest/kotlintest
     * **/
    const val kotlintestDatagen = "$kotestArtifact-datagen:$placeholderVersion"



    /** https://www.spekframework.org **/
    const val spekDslJvm = "org.spekframework.spek2:spek-dsl-jvm:$placeholderVersion"
    /** https://www.spekframework.org **/
    const val spekRunner = "org.spekframework.spek2:spek-runner-junit5:$placeholderVersion"

    /**
     * Tasty mocking framework for unit tests in Java
     * https://site.mockito.org/
     */
    const val mockitoCore = "org.mockito:mockito-core:$placeholderVersion"
    const val mockitoAndroid = "org.mockito:mockito-android:$placeholderVersion"
    const val mockitoInline = "org.mockito:mockito-inline:$placeholderVersion"


    /**
     * Using Mockito with Kotlin
     * https://github.com/nhaarman/mockito-kotlin
     */
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:$placeholderVersion"

    /***
     * mocking library for Kotlin
     * http://mockk.io
     */
    const val mokk = "io.mockk:mockk:$placeholderVersion"

    /**
     * Strikt is an assertion library for Kotlin intended for use with a test runner such as JUnit or Spek.
     * https://strikt.io/
     */
    const val strikt = "io.strikt:strikt-core:$placeholderVersion"

}
