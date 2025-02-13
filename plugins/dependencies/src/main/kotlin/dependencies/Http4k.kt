import de.fayard.refreshVersions.core.DependencyGroup


object Http4k : DependencyGroup(
    group = "org.http4k",
    usePlatformConstraints = false,
    rawRules = """
        org.http4k:http4k-*
            ^^^^^^
        org.http4k:http4k-connect-*
                   ^^^^^^^^^^^^^^
    """.trimIndent()
) {
    val bom = module("http4k-bom", isBom = true)

    val config = module("http4k-config")
    val core = module("http4k-core")
    val incubator = module("http4k-incubator")
    val multipart = module("http4k-multipart")
    val realtimeCore = module("http4k-realtime-core")
    val webhook = module("http4k-webhook")

    val api = Api
    object Api : DependencyGroup(group, usePlatformConstraints = true) {
        val cloudevents = module("http4k-api-cloudevents")
        val graphql = module("http4k-api-graphql")
        val jsonrpc = module("http4k-api-jsonrpc")
        val jsonschema = module("http4k-api-jsonschema")
        val openapi = module("http4k-api-openapi")
        val uiRedoc = module("http4k-api-ui-redoc")
        val uiSwagger = module("http4k-api-ui-swagger")
    }

    val bridge = Bridge
    object Bridge : DependencyGroup(group, usePlatformConstraints = true) {
        val helidon = module("http4k-bridge-helidon")
        val jakarta = module("http4k-bridge-jakarta")
        val ktor = module("http4k-bridge-ktor")
        val micronaut = module("http4k-bridge-micronaut")
        val ratpack = module("http4k-bridge-ratpack")
        val servlet = module("http4k-bridge-servlet")
        val spring = module("http4k-bridge-spring")
        val vertx = module("http4k-bridge-vertx")
    }

    val client = Client
    object Client : DependencyGroup(group, usePlatformConstraints = true) {
        val apache = module("http4k-client-apache")
        val apacheAsync = module("http4k-client-apache-async")
        val apache4 = module("http4k-client-apache4")
        val apache4Async = module("http4k-client-apache4-async")
        val fuel = module("http4k-client-fuel")
        val helidon = module("http4k-client-helidon")
        val jetty = module("http4k-client-jetty")
        val okhttp = module("http4k-client-okhttp")
        val websocket = module("http4k-client-websocket")
    }

    val format = Format
    object Format : DependencyGroup(group, usePlatformConstraints = true) {
        val argo = module("http4k-format-argo")
        val core = module("http4k-format-core")
        val dataframe = module("http4k-format-dataframe")
        val gson = module("http4k-format-gson")
        val jackson = module("http4k-format-jackson")
        val jacksonCsv = module("http4k-format-jackson-csv")
        val jacksonXml = module("http4k-format-jackson-xml")
        val jacksonYaml = module("http4k-format-jackson-yaml")
        val klaxon = module("http4k-format-klaxon")
        val kondorJson = module("http4k-format-kondor-json")
        val kotlinxSerialization = module("http4k-format-kotlinx-serialization")
        val moshi = module("http4k-format-moshi")
        val moshiYaml = module("http4k-format-moshi-yaml")
        val xml = module("http4k-format-xml")
    }

    val ops = Ops
    object Ops : DependencyGroup(group, usePlatformConstraints = true) {
        val core = module("http4k-ops-core")
        val failsafe = module("http4k-ops-failsafe")
        val micrometer = module("http4k-ops-micrometer")
        val opentelemetry = module("http4k-ops-opentelemetry")
        val resilience4j = module("http4k-ops-resilience4j")
    }

    val platform = Platform
    object Platform : DependencyGroup(group, usePlatformConstraints = true) {
        val aws = module("http4k-platform-aws")
        val azure = module("http4k-platform-azure")
        val core = module("http4k-platform-core")
        val gcp = module("http4k-platform-gcp")
        val k8s = module("http4k-platform-k8s")
    }

    val security = Security
    object Security : DependencyGroup(group, usePlatformConstraints = true) {
        val core = module("http4k-security-core")
        val digest = module("http4k-security-digest")
        val oauth = module("http4k-security-oauth")
    }

    val server = Server
    object Server : DependencyGroup(group, usePlatformConstraints = true) {
        val apache = module("http4k-server-apache")
        val apache4 = module("http4k-server-apache4")
        val helidon = module("http4k-server-helidon")
        val jetty = module("http4k-server-jetty")
        val jetty11 = module("http4k-server-jetty11")
        val ktorcio = module("http4k-server-ktorcio")
        val ktornetty = module("http4k-server-ktornetty")
        val netty = module("http4k-server-netty")
        val ratpack = module("http4k-server-ratpack")
        val undertow = module("http4k-server-undertow")
        val websocket = module("http4k-server-websocket")
    }

    val serverless = Serverless
    object Serverless : DependencyGroup(group, usePlatformConstraints = true) {
        val alibaba = module("http4k-serverless-alibaba")
        val azure = module("http4k-serverless-azure")
        val core = module("http4k-serverless-core")
        val gcf = module("http4k-serverless-gcf")
        val lambda = module("http4k-serverless-lambda")
        val lambdaRuntime = module("http4k-serverless-lambda-runtime")
        val openwhisk = module("http4k-serverless-openwhisk")
        val tencent = module("http4k-serverless-tencent")
    }

    val template = Template
    object Template : DependencyGroup(group, usePlatformConstraints = true) {
        val core = module("http4k-template-core")
        val freemarker = module("http4k-template-freemarker")
        val handlebars = module("http4k-template-handlebars")
        val jte = module("http4k-template-jte")
        val pebble = module("http4k-template-pebble")
        val pug4j = module("http4k-template-pug4j")
        val rocker = module("http4k-template-rocker")
        val thymeleaf = module("http4k-template-thymeleaf")
    }

    val testing = Testing
    object Testing : DependencyGroup(group, usePlatformConstraints = true) {
        val approval = module("http4k-testing-approval")
        val chaos = module("http4k-testing-chaos")
        val hamkrest = module("http4k-testing-hamkrest")
        val kotest = module("http4k-testing-kotest")
        val playwright = module("http4k-testing-playwright")
        val servirtium = module("http4k-testing-servirtium")
        val strikt = module("http4k-testing-strikt")
        val tracerbullet = module("http4k-testing-tracerbullet")
        val webdriver = module("http4k-testing-webdriver")
    }

    val tools = Tools
    object Tools : DependencyGroup(group, usePlatformConstraints = true) {
        val trafficCapture = module("http4k-tools-traffic-capture")
    }

    val web = Web
    object Web : DependencyGroup(group, usePlatformConstraints = true) {
        val datastar = module("http4k-web-datastar")
        val htmx = module("http4k-web-htmx")
    }
}
