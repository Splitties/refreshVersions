import de.fayard.refreshVersions.core.internal.DependencyGroup

private val GROUP = "org.http4k"

object Http4k : DependencyGroup(
    group = GROUP,
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

    val aws get() = module("http4k-aws")
    val cloudnative get() = module("http4k-cloudnative")
    val contract get() = module("http4k-contract")
    val core get() = module("http4k-core")
    val graphql get() = module("http4k-graphql")
    val incubator get() = module("http4k-incubator")
    val jsonrpc get() = module("http4k-jsonrpc")
    val metricsMicrometer get() = module("http4k-metrics-micrometer")
    val multipart get() = module("http4k-multipart")
    val opentelemetry get() = module("http4k-opentelemetry")
    val realtimeCore get() = module("http4k-realtime-core")
    val resilience4j get() = module("http4k-resilience4j")
    val securityOauth get() = module("http4k-security-oauth")

    val client = Client

    object Client : DependencyGroup(GROUP, usePlatformConstraints = true) {
        val apache get() = module("http4k-client-apache")
        val apacheAsync get() = module("http4k-client-apache-async")
        val apache4 get() = module("http4k-client-apache4")
        val apache4Async get() = module("http4k-client-apache4-async")
        val jetty get() = module("http4k-client-jetty")
        val okhttp get() = module("http4k-client-okhttp")
        val websocket get() = module("http4k-client-websocket")
    }

    val format = Format

    object Format : DependencyGroup(GROUP, usePlatformConstraints = true) {
        val argo get() = module("http4k-format-argo")
        val core get() = module("http4k-format-core")
        val gson get() = module("http4k-format-gson")
        val jackson get() = module("http4k-format-jackson")
        val jacksonXml get() = module("http4k-format-jackson-xml")
        val jacksonYaml get() = module("http4k-format-jackson-yaml")
        val klaxon get() = module("http4k-format-klaxon")
        val kotlinxSerialization get() = module("http4k-format-kotlinx-serialization")
        val moshi get() = module("http4k-format-moshi")
        val xml get() = module("http4k-format-xml")
    }


    val server = Server

    object Server : DependencyGroup(GROUP, usePlatformConstraints = true) {
        val apache get() = module("http4k-server-apache")
        val apache4 get() = module("http4k-server-apache4")
        val jetty get() = module("http4k-server-jetty")
        val ktorcio get() = module("http4k-server-ktorcio")
        val ktornetty get() = module("http4k-server-ktornetty")
        val netty get() = module("http4k-server-netty")
        val ratpack get() = module("http4k-server-ratpack")
        val undertow get() = module("http4k-server-undertow")
    }

    val serverless = Serverless

    object Serverless : DependencyGroup(GROUP, usePlatformConstraints = true) {
        val alibaba get() = module("http4k-serverless-alibaba")
        val azure get() = module("http4k-serverless-azure")
        val gcf get() = module("http4k-serverless-gcf")
        val lambda get() = module("http4k-serverless-lambda")
        val lambdaRuntime get() = module("http4k-serverless-lambda-runtime")
        val openwhisk get() = module("http4k-serverless-openwhisk")
        val tencent get() = module("http4k-serverless-tencent")
    }

    val template = Template

    object Template : DependencyGroup(GROUP, usePlatformConstraints = true) {
        val core get() = module("http4k-template-core")
        val dust get() = module("http4k-template-dust")
        val freemarker get() = module("http4k-template-freemarker")
        val handlebars get() = module("http4k-template-handlebars")
        val jade4j get() = module("http4k-template-jade4j")
        val pebble get() = module("http4k-template-pebble")
        val thymeleaf get() = module("http4k-template-thymeleaf")
    }

    val testing = Testing

    object Testing : DependencyGroup(GROUP, usePlatformConstraints = true) {
        val approval get() = module("http4k-testing-approval")
        val chaos get() = module("http4k-testing-chaos")
        val hamkrest get() = module("http4k-testing-hamkrest")
        val kotest get() = module("http4k-testing-kotest")
        val servirtium get() = module("http4k-testing-servirtium")
        val strikt get() = module("http4k-testing-strikt")
        val webdriver get() = module("http4k-testing-webdriver")
    }
}

