@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyGroup
import org.gradle.kotlin.dsl.IsNotADependency

/**
 * GraphQL client that generates Kotlin and Java models from GraphQL queries.
 *
 * - [Official website here](https://www.apollographql.com/docs/kotlin/)
 * - GitHub page: [apollographql/apollo-kotlin](https://github.com/apollographql/apollo-kotlin)
 * - [GitHub Releases](https://github.com/apollographql/apollo-kotlin/releases)
 */
object ApolloGraphQL : DependencyGroup(
    group = "com.apollographql.apollo3",
    rawRules = """
        com.apollographql.apollo3:apollo-*
            ^^^^^^^^^^^^^
    """.trimIndent()
), IsNotADependency {
    /**
     * A collection of adapters for custom scalar types.
     *
     * [Documentation](https://www.apollographql.com/docs/kotlin/essentials/custom-scalars#apollo-provided-adapters)
     */
    val adapters = module("apollo-adapters")

    /**
     * Use the generated models and parsers without the runtime and
     * use your network layer of choice for the HTTP calls
     *
     * [Documentation](https://www.apollographql.com/docs/kotlin/advanced/no-runtime)
     */
    val api = module("apollo-api")

    /**
     * Represents a GraphQL document in a type-safe, machine-readable format.
     *
     * [Documentation](https://www.apollographql.com/docs/kotlin/advanced/apollo-ast)
     */
    val ast = module("apollo-ast")

    /**
     * HTTP cahche support (JVM only)
     *
     * [Documentation](https://www.apollographql.com/docs/kotlin/caching/http-cache)
     */
    val httpCache = module("apollo-http-cache")

    /**
     * `IdlingResource` to help you write UI tests with Espresso.
     *
     * [Documentation](https://www.apollographql.com/docs/kotlin/testing/ui-tests)
     */
    val idlingResource = module("apollo-idling-resource")

    /**
     * `MockServer` implements an HTTP server that you can use to mock responses.
     *
     * [Documentation](https://www.apollographql.com/docs/kotlin/testing/mocking-http-responses)
     */
    val mockserver = module("apollo-mockserver")

    /**
     * A normalized, in-memory cache for storing objects from your GraphQL operations.
     *
     * [Documentation](https://www.apollographql.com/docs/kotlin/caching/normalized-cache#in-memory-cache)
     */
    val normalizedCache = module("apollo-normalized-cache")

    /**
     * Uses [SQLDelight](https://github.com/cashapp/sqldelight) to store data persistently.
     *
     * [Documentation](https://www.apollographql.com/docs/kotlin/caching/normalized-cache#sqlite-cache)
     */
    val normalizedCacheSqlite = module("apollo-normalized-cache-sqlite")

    /**
     * Main runtime dependency.
     *
     * [Documentation](https://www.apollographql.com/docs/kotlin/#getting-started)
     */
    val runtime = module("apollo-runtime")

    /**
     * `QueueTestNetworkTransport` is a high-level test API that enables you to
     *  specify the GraphQL responses that are returned by your `ApolloClient` instance.
     *
     * [Documentation](https://www.apollographql.com/docs/kotlin/testing/mocking-graphql-responses)
     */
    val testingSupport = module("apollo-testing-support")
}
