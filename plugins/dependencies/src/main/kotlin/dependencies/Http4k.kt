@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import org.gradle.api.Incubating
import org.gradle.kotlin.dsl.IsNotADependency

@Incubating
/**
 *  The functional toolkit for Kotlin HTTP applications
 *  http4k provides a simple and uniform way to serve and consume HTTP services.
 *
 * - [Official website here](https://www.http4k.org/)
 * - GitHub page: [http4k/http4k](https://github.com/http4k/http4k)
 * - [Changelog here](https://www.http4k.org/changelog/)
 *
 * Usage:
 * ```
 * implementation(platform(Http4k.bom))
 * implementation(Http4k.core)
 * ```
 */
object Http4k {

    private const val prefix = "org.http4k:http4k"

    /**
     * * Usage:
     * ```
     * implementation(platform(Http4k.bom))
     * implementation(Http4k.core)
     * ```
     */
    const val bom = "org.http4k:http4k-bom:_"

    /**
     * [AWS](https://www.http4k.org/guide/modules/aws/)
     */
    const val aws = "$prefix-aws"

    const val incubator = "$prefix-incubator"

    /**
     * [JSON RPC](https://www.http4k.org/guide/modules/jsonrpc/)
     */
    const val jsonrpc = "$prefix-jsonrpc"

    /**
     * [Metrics](https://www.http4k.org/guide/modules/metrics/)
     */
    const val metrics_micrometer = "$prefix-metrics-micrometer"

    /**
     * [Multipart forms](https://www.http4k.org/guide/modules/multipart/)
     */
    const val multipart = "$prefix-multipart"

    /**
     * [Resilience](https://www.http4k.org/guide/modules/resilience/)
     */
    const val resilience4j = "$prefix-resilience4j"

    /**
     * [OAuth documentation](https://www.http4k.org/guide/modules/oauth/)
     */
    const val security_oauth = "$prefix-security-oauth"

    /**
     * [Cloud native Configuration](https://www.http4k.org/guide/modules/cloud_native/)
     */
    const val cloudnative = "$prefix-cloudnative"

    /**
     * [Typesafe contracts - OpenAPI3](https://www.http4k.org/guide/modules/contracts/)
     */
    const val contract = "$prefix-contract"

    /**
     * [Core documentation](https://www.http4k.org/guide/modules/core/)
     */
    const val core = "$prefix-core"

    /**
     * [HTTP & Websocket clients](https://www.http4k.org/guide/modules/clients/)
     */
    val client = Client

    object Client : IsNotADependency {
        const val apache = "$prefix-client-apache"
        const val apacheAsync = "$prefix-client-apache-async"
        const val apache4 = "$prefix-client-apache4"
        const val apache4Async = "$prefix-client-apache4-async"
        const val jetty = "$prefix-client-jetty"
        const val okhttp = "$prefix-client-okhttp"
        const val websocket = "$prefix-client-websocket"
    }


    /**
     * - [Json handling](https://www.http4k.org/guide/modules/json/)
     * - [XML handling](https://www.http4k.org/guide/modules/xml/)
     * - [YAML handling](https://www.http4k.org/guide/modules/yaml/)
     */
    val format = Format

    object Format : IsNotADependency {
        const val argo = "$prefix-format-argo"
        const val gson = "$prefix-format-gson"
        const val jackson = "$prefix-format-jackson"
        const val jacksonXml = "$prefix-format-jackson-xml"
        const val jacksonYaml = "$prefix-format-jackson-yaml"
        const val kotlinxSerialization = "$prefix-format-kotlinx-serialization"
        const val moshi = "$prefix-format-moshi"
        const val xml = "$prefix-format-xml"
    }


    /**
     * [Server backend](https://www.http4k.org/guide/modules/servers/)
     */
    val server = Server

    object Server : IsNotADependency {
        const val apache = "$prefix-server-apache"
        const val apache4 = "$prefix-server-apache4"
        const val jetty = "$prefix-server-jetty"
        const val ktorcio = "$prefix-server-ktorcio"
        const val ktornetty = "$prefix-server-ktornetty"
        const val netty = "$prefix-server-netty"
        const val ratpack = "$prefix-server-ratpack"
        const val undertow = "$prefix-server-undertow"
    }

    /**
     * [Serverless backend](https://www.http4k.org/guide/modules/serverless/)
     */
    val serverless = Serverless

    object Serverless : IsNotADependency {
        const val gcf = "$prefix-serverless-gcf"
        const val lambda = "$prefix-serverless-lambda"
        const val openwhisk = "$prefix-serverless-openwhisk"
    }

    /**
     * [Templating](https://www.http4k.org/guide/modules/templating/)
     */
    val template = Template

    object Template : IsNotADependency {
        const val dust = "$prefix-template-dust"
        const val freemarker = "$prefix-template-freemarker"
        const val handlebars = "$prefix-template-handlebars"
        const val jade4j = "$prefix-template-jade4j"
        const val pebble = "$prefix-template-pebble"
        const val thymeleaf = "$prefix-template-thymeleaf"
    }

    val testing = Testing

    object Testing : IsNotADependency {
        /**
         * [Approval testing](https://www.http4k.org/guide/modules/approvaltests/)
         */
        const val approval = "$prefix-testing-approval"

        /**
         * [Chaos Testing](https://www.http4k.org/guide/modules/chaos/)
         */
        const val chaos = "$prefix-testing-chaos"

        /**
         * [Hamkrest matchers](https://www.http4k.org/guide/modules/hamkrest/)
         */
        const val hamkrest = "$prefix-testing-hamkrest"

        /**
         * [Kotest](https://www.http4k.org/guide/modules/kotest/)
         */
        const val kotest = "$prefix-testing-kotest"

        /**
         * [Service Virtualisation](https://www.http4k.org/guide/modules/servicevirtualisation/)
         */
        const val servirtium = "$prefix-testing-servirtium"

        /**
         * [WebDriver](https://www.http4k.org/guide/modules/webdriver/)
         */
        const val webdriver = "$prefix-testing-webdriver"
    }
}
