import de.fayard.refreshVersions.core.internal.DependencyGroup


object Http4k : DependencyGroup(
    group = "org.http4k",
    usePlatformConstraints = false,
    rawRule = """
        org.http4k:http4k-*
            ^^^^^^
    """.trimIndent()
) {
    val bom: String get() {
        usePlatformConstraints = true
        return "org.http4k:http4k-bom:_"
    }

    val aws by module("http4k-aws")
    val cloudnative by module("http4k-cloudnative")
    val contract by module("http4k-contract")
    val core by module("http4k-core")
    val graphql by module("http4k-graphql")
    val incubator by module("http4k-incubator")
    val jsonrpc by module("http4k-jsonrpc")
    val metricsMicrometer by module("http4k-metrics-micrometer")
    val multipart by module("http4k-multipart")
    val opentelemetry by module("http4k-opentelemetry")
    val realtimeCore by module("http4k-realtime-core")
    val resilience4j by module("http4k-resilience4j")
    val securityOauth by module("http4k-security-oauth")

    val client = Client

    object Client : DependencyGroup(group, usePlatformConstraints = true) {
        val apache by module("http4k-client-apache")
        val apacheAsync by module("http4k-client-apache-async")
        val apache4 by module("http4k-client-apache4")
        val apache4Async by module("http4k-client-apache4-async")
        val jetty by module("http4k-client-jetty")
        val okhttp by module("http4k-client-okhttp")
        val websocket by module("http4k-client-websocket")
    }

    val format = Format

    object Format : DependencyGroup(group, usePlatformConstraints = true) {
        val argo by module("http4k-format-argo")
        val core by module("http4k-format-core")
        val gson by module("http4k-format-gson")
        val jackson by module("http4k-format-jackson")
        val jacksonXml by module("http4k-format-jackson-xml")
        val jacksonYaml by module("http4k-format-jackson-yaml")
        val klaxon by module("http4k-format-klaxon")
        val kotlinxSerialization by module("http4k-format-kotlinx-serialization")
        val moshi by module("http4k-format-moshi")
        val xml by module("http4k-format-xml")
    }


    val server = Server

    object Server : DependencyGroup(group, usePlatformConstraints = true) {
        val apache by module("http4k-server-apache")
        val apache4 by module("http4k-server-apache4")
        val jetty by module("http4k-server-jetty")
        val ktorcio by module("http4k-server-ktorcio")
        val ktornetty by module("http4k-server-ktornetty")
        val netty by module("http4k-server-netty")
        val ratpack by module("http4k-server-ratpack")
        val undertow by module("http4k-server-undertow")
    }

    val serverless = Serverless

    object Serverless : DependencyGroup(group, usePlatformConstraints = true) {
        val alibaba by module("http4k-serverless-alibaba")
        val azure by module("http4k-serverless-azure")
        val gcf by module("http4k-serverless-gcf")
        val lambda by module("http4k-serverless-lambda")
        val lambdaRuntime by module("http4k-serverless-lambda-runtime")
        val openwhisk by module("http4k-serverless-openwhisk")
        val tencent by module("http4k-serverless-tencent")
    }

    val template = Template

    object Template : DependencyGroup(group, usePlatformConstraints = true) {
        val core by module("http4k-template-core")
        val dust by module("http4k-template-dust")
        val freemarker by module("http4k-template-freemarker")
        val handlebars by module("http4k-template-handlebars")
        val jade4j by module("http4k-template-jade4j")
        val pebble by module("http4k-template-pebble")
        val thymeleaf by module("http4k-template-thymeleaf")
    }

    val testing = Testing

    object Testing : DependencyGroup(group, usePlatformConstraints = true) {
        val approval by module("http4k-testing-approval")
        val chaos by module("http4k-testing-chaos")
        val hamkrest by module("http4k-testing-hamkrest")
        val kotest by module("http4k-testing-kotest")
        val servirtium by module("http4k-testing-servirtium")
        val strikt by module("http4k-testing-strikt")
        val webdriver by module("http4k-testing-webdriver")
    }
}

