import de.fayard.refreshVersions.core.internal.DependencyGroup
import org.gradle.kotlin.dsl.IsNotADependency

object Spring : IsNotADependency {

    val boms = Boms

    object Boms: IsNotADependency {
        val dependencies = "org.springframework.boot:spring-boot-dependencies:_"
        val geode = "org.springframework.geode:spring-geode-bom:_"
        val springclmoud = "org.springframework.cloud:spring-cloud-dependencies:_"
    }

    val kafka = "org.springframework.kafka:spring-kafka"

    val kafkaTest = "org.springframework.kafka:spring-kafka-test"

    val springRestdocsWebtestclient = "org.springframework.restdocs:spring-restdocs-webtestclient"

    val geode = "org.springframework.geode:spring-geode-starter"

    val rabbitTest = "org.springframework.amqp:spring-rabbit-test"

    val batchTest = "org.springframework.batch:spring-batch-test"

    val reactor = Reactor

    object Reactor : DependencyGroup("io.projectreactor") {
        val kotlin = "io.projectreactor.kotlin:reactor-kotlin-extensions"

        val test = "io.projectreactor:reactor-test"
    }

    val data = Data

    object Data : DependencyGroup("org.springframework.data", usePlatformConstraints = true) {
        val halExplorer = "org.springframework.data:spring-data-rest-hal-explorer"
    }


    val springCloud = SpringCloud

    object SpringCloud : DependencyGroup("io.pivotal.spring.cloud", usePlatformConstraints = true) {
        val circuitBreaker by module("spring-cloud-services-starter-circuit-breaker")

        val configClient by module("spring-cloud-services-starter-config-client")

        val serviceRegistry by module("spring-cloud-services-starter-service-registry")
    }

    val boot = Boot

    object Boot : DependencyGroup("org.springframework.boot", usePlatformConstraints = true) {
        val devTools = "org.springframework.boot:spring-boot-devtools"

        val configurationProcessor = "org.springframework.boot:spring-boot-configuration-processor"

        val activemq by module("spring-boot-starter-activemq")

        val actuator by module("spring-boot-starter-actuator")

        val amqp by module("spring-boot-starter-amqp")

        val artemis by module("spring-boot-starter-artemis")

        val batch by module("spring-boot-starter-batch")

        val cache by module("spring-boot-starter-cache")

        val data = Data

        object Data : IsNotADependency {
            val cassandra by module("spring-boot-starter-data-cassandra")

            val cassandraReactive by module("spring-boot-starter-data-cassandra-reactive")

            val couchbase by module("spring-boot-starter-data-couchbase")

            val couchbase_reactive by module("spring-boot-starter-data-couchbase-reactive")

            val elasticsearch by module("spring-boot-starter-data-elasticsearch")

            val jdbc by module("spring-boot-starter-data-jdbc")

            val jpa by module("spring-boot-starter-data-jpa")

            val ldap by module("spring-boot-starter-data-ldap")

            val mongodb by module("spring-boot-starter-data-mongodb")

            val mongodbReactive by module("spring-boot-starter-data-mongodb-reactive")

            val neo4j by module("spring-boot-starter-data-neo4j")

            val r2dbc by module("spring-boot-starter-data-r2dbc")

            val redis by module("spring-boot-starter-data-redis")

            val redis_reactive by module("spring-boot-starter-data-redis-reactive")

            val rest by module("spring-boot-starter-data-rest")

            val solr by module("spring-boot-starter-data-solr")
        }

        val freemarker by module("spring-boot-starter-freemarker")

        val groovyTemplates by module("spring-boot-starter-groovy-templates")

        val hateoas by module("spring-boot-starter-hateoas")

        val integration by module("spring-boot-starter-integration")

        val jdbc by module("spring-boot-starter-jdbc")

        val jersey by module("spring-boot-starter-jersey")

        val jooq by module("spring-boot-starter-jooq")

        val mail by module("spring-boot-starter-mail")

        val mustache by module("spring-boot-starter-mustache")

        val oauth2Client by module("spring-boot-starter-oauth2-client")

        val oauth2ResourceServer by module("spring-boot-starter-oauth2-resource-server")

        val quartz by module("spring-boot-starter-quartz")

        val rsocket by module("spring-boot-starter-rsocket")

        val security by module("spring-boot-starter-security")

        val test by module("spring-boot-starter-test")

        val thymeleaf by module("spring-boot-starter-thymeleaf")

        val validation by module("spring-boot-starter-validation")

        val webServices by module("spring-boot-starter-web-services")

        val webflux by module("spring-boot-starter-webflux")

        val websocket by module("spring-boot-starter-websocket")
    }

    val cloud = Cloud

    object Cloud : DependencyGroup("org.springframework.cloud", usePlatformConstraints = true) {

        val bus by module("spring-cloud-bus")

        val cloudfoundry_discovery by module("spring-cloud-cloudfoundry-discovery")

        val config_server by module("spring-cloud-config-server")

        val function_web by module("spring-cloud-function-web")

        val gcp_starter by module("spring-cloud-gcp-starter")

        val gcp_starter_pubsub by module("spring-cloud-gcp-starter-pubsub")

        val gcp_starter_storage by module("spring-cloud-gcp-starter-storage")

        val starter by module("spring-cloud-starter")

        val aws = Aws

        object Aws : IsNotADependency {
            val aws by module("spring-cloud-starter-aws")

            val jdbc by module("spring-cloud-starter-aws-jdbc")

            val messaging by module("spring-cloud-starter-aws-messaging")
        }

        val circuitbreakerReactorResilience4J by module("spring-cloud-starter-circuitbreaker-reactor-resilience4j")

        val config by module("spring-cloud-starter-config")

        val consulConfig by module("spring-cloud-starter-consul-config")

        val consulDiscovery by module("spring-cloud-starter-consul-discovery")

        val contractStubRunner by module("spring-cloud-starter-contract-stub-runner")

        val contractVerifier by module("spring-cloud-starter-contract-verifier")

        val gateway by module("spring-cloud-starter-gateway")

        val loadbalancer by module("spring-cloud-starter-loadbalancer")
        val netflix = Netflix

        object Netflix : IsNotADependency {

            val eurekaClient by module("spring-cloud-starter-netflix-eureka-client")

            val eurekaServer by module("spring-cloud-starter-netflix-eureka-server")

            val hystrix by module("spring-cloud-starter-netflix-hystrix")

            val hystrixDashboard by module("spring-cloud-starter-netflix-hystrix-dashboard")

            val ribbon by module("spring-cloud-starter-netflix-ribbon")

            val turbine by module("spring-cloud-starter-netflix-turbine")

            val turbineStream by module("spring-cloud-starter-netflix-turbine-stream")

            val zuul by module("spring-cloud-starter-netflix-zuul")
        }

        val oauth2 by module("spring-cloud-starter-oauth2")

        val openServiceBroker by module("spring-cloud-starter-open-service-broker")

        val openfeign by module("spring-cloud-starter-openfeign")

        val security by module("spring-cloud-starter-security")

        val sleuth by module("spring-cloud-starter-sleuth")

        val task by module("spring-cloud-starter-task")

        val vault_config by module("spring-cloud-starter-vault-config")

        val zipkin by module("spring-cloud-starter-zipkin")

        val zookeeperConfig by module("spring-cloud-starter-zookeeper-config")

        val zookeeperDiscovery by module("spring-cloud-starter-zookeeper-discovery")

        val stream = Stream

        object Stream : IsNotADependency {
            val stream by module("spring-cloud-stream")

            val binderKafka by module("spring-cloud-stream-binder-kafka")

            val binderKafkaStreams by module("spring-cloud-stream-binder-kafka-streams")
        }

        val streamBinderRabbit by module("spring-cloud-stream-binder-rabbit")
    }

    val integration = Integration

    object Integration : DependencyGroup("org.springframework.integration", usePlatformConstraints = true) {

        val amqp by module("spring-integration-amqp")

        val gemfire by module("spring-integration-gemfire")

        val jdbc by module("spring-integration-jdbc")

        val jms by module("spring-integration-jms")

        val jpa by module("spring-integration-jpa")

        val kafka by module("spring-integration-kafka")

        val mail by module("spring-integration-mail")

        val mongodb by module("spring-integration-mongodb")

        val r2dbc by module("spring-integration-r2dbc")

        val redis by module("spring-integration-redis")

        val rsocket by module("spring-integration-rsocket")

        val security by module("spring-integration-security")

        val stomp by module("spring-integration-stomp")

        val test by module("spring-integration-test")

        val webflux by module("spring-integration-webflux")

        val websocket by module("spring-integration-websocket")

        val ws by module("spring-integration-ws")
    }

    val security = Security

    object Security : DependencyGroup("org.springframework.security", usePlatformConstraints = true) {
        val spring_security_messaging by module("spring-security-messaging")

        val spring_security_rsocket by module("spring-security-rsocket")

        val spring_security_test by module("spring-security-test")
    }

    val session = Session

    object Session : DependencyGroup("org.springframework.session", usePlatformConstraints = true) {
        val dataRedis by module("spring-session-data-redis")

        val jdbc by module("spring-session-jdbc")
    }

}
