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
        val circuitBreaker get() = module("spring-cloud-services-starter-circuit-breaker")

        val configClient get() = module("spring-cloud-services-starter-config-client")

        val serviceRegistry get() = module("spring-cloud-services-starter-service-registry")
    }

    val boot = Boot

    object Boot : DependencyGroup("org.springframework.boot", usePlatformConstraints = true) {
        val devTools = "org.springframework.boot:spring-boot-devtools"

        val configurationProcessor = "org.springframework.boot:spring-boot-configuration-processor"

        val activemq get() = module("spring-boot-starter-activemq")

        val actuator get() = module("spring-boot-starter-actuator")

        val amqp get() = module("spring-boot-starter-amqp")

        val artemis get() = module("spring-boot-starter-artemis")

        val batch get() = module("spring-boot-starter-batch")

        val cache get() = module("spring-boot-starter-cache")

        val data = Data

        object Data : IsNotADependency {
            val cassandra get() = module("spring-boot-starter-data-cassandra")

            val cassandraReactive =
                module("spring-boot-starter-data-cassandra-reactive")

            val couchbase get() = module("spring-boot-starter-data-couchbase")

            val couchbase_reactive =
                module("spring-boot-starter-data-couchbase-reactive")

            val elasticsearch =
                module("spring-boot-starter-data-elasticsearch")

            val jdbc get() = module("spring-boot-starter-data-jdbc")

            val jpa get() = module("spring-boot-starter-data-jpa")

            val ldap get() = module("spring-boot-starter-data-ldap")

            val mongodb get() = module("spring-boot-starter-data-mongodb")

            val mongodbReactive =
                module("spring-boot-starter-data-mongodb-reactive")

            val neo4j get() = module("spring-boot-starter-data-neo4j")

            val r2dbc get() = module("spring-boot-starter-data-r2dbc")

            val redis get() = module("spring-boot-starter-data-redis")

            val redis_reactive =
                module("spring-boot-starter-data-redis-reactive")

            val rest get() = module("spring-boot-starter-data-rest")

            val solr get() = module("spring-boot-starter-data-solr")
        }

        val freemarker get() = module("spring-boot-starter-freemarker")

        val groovyTemplates get() = module("spring-boot-starter-groovy-templates")

        val hateoas get() = module("spring-boot-starter-hateoas")

        val integration get() = module("spring-boot-starter-integration")

        val jdbc get() = module("spring-boot-starter-jdbc")

        val jersey get() = module("spring-boot-starter-jersey")

        val jooq get() = module("spring-boot-starter-jooq")

        val mail get() = module("spring-boot-starter-mail")

        val mustache get() = module("spring-boot-starter-mustache")

        val oauth2Client get() = module("spring-boot-starter-oauth2-client")

        val oauth2ResourceServer =
            module("spring-boot-starter-oauth2-resource-server")

        val quartz get() = module("spring-boot-starter-quartz")

        val rsocket get() = module("spring-boot-starter-rsocket")

        val security get() = module("spring-boot-starter-security")

        val test get() = module("spring-boot-starter-test")

        val thymeleaf get() = module("spring-boot-starter-thymeleaf")

        val validation get() = module("spring-boot-starter-validation")

        val webServices get() = module("spring-boot-starter-web-services")

        val webflux get() = module("spring-boot-starter-webflux")

        val websocket get() = module("spring-boot-starter-websocket")
    }

    val cloud = Cloud

    object Cloud : DependencyGroup("org.springframework.cloud", usePlatformConstraints = true) {

        val bus get() = module("spring-cloud-bus")

        val cloudfoundry_discovery =
            module("spring-cloud-cloudfoundry-discovery")

        val config_server get() = module("spring-cloud-config-server")

        val function_web get() = module("spring-cloud-function-web")

        val gcp_starter get() = module("spring-cloud-gcp-starter")

        val gcp_starter_pubsub get() = module("spring-cloud-gcp-starter-pubsub")

        val gcp_starter_storage get() = module("spring-cloud-gcp-starter-storage")

        val starter get() = module("spring-cloud-starter")

        val aws = Aws

        object Aws : IsNotADependency {
            val aws get() = module("spring-cloud-starter-aws")

            val jdbc get() = module("spring-cloud-starter-aws-jdbc")

            val messaging get() = module("spring-cloud-starter-aws-messaging")
        }

        val circuitbreakerReactorResilience4J =
            module("spring-cloud-starter-circuitbreaker-reactor-resilience4j")

        val config get() = module("spring-cloud-starter-config")

        val consulConfig get() = module("spring-cloud-starter-consul-config")

        val consulDiscovery =
            module("spring-cloud-starter-consul-discovery")

        val contractStubRunner =
            module("spring-cloud-starter-contract-stub-runner")

        val contractVerifier =
            module("spring-cloud-starter-contract-verifier")

        val gateway get() = module("spring-cloud-starter-gateway")

        val loadbalancer get() = module("spring-cloud-starter-loadbalancer")
        val netflix = Netflix

        object Netflix : IsNotADependency {

            val eurekaClient =
                module("spring-cloud-starter-netflix-eureka-client")

            val eurekaServer =
                module("spring-cloud-starter-netflix-eureka-server")

            val hystrix =
                module("spring-cloud-starter-netflix-hystrix")

            val hystrixDashboard =
                module("spring-cloud-starter-netflix-hystrix-dashboard")

            val ribbon =
                module("spring-cloud-starter-netflix-ribbon")

            val turbine =
                module("spring-cloud-starter-netflix-turbine")

            val turbineStream =
                module("spring-cloud-starter-netflix-turbine-stream")

            val zuul get() = module("spring-cloud-starter-netflix-zuul")
        }

        val oauth2 get() = module("spring-cloud-starter-oauth2")

        val openServiceBroker =
            module("spring-cloud-starter-open-service-broker")

        val openfeign get() = module("spring-cloud-starter-openfeign")

        val security get() = module("spring-cloud-starter-security")

        val sleuth get() = module("spring-cloud-starter-sleuth")

        val task get() = module("spring-cloud-starter-task")

        val vault_config get() = module("spring-cloud-starter-vault-config")

        val zipkin get() = module("spring-cloud-starter-zipkin")

        val zookeeperConfig =
            module("spring-cloud-starter-zookeeper-config")

        val zookeeperDiscovery =
            module("spring-cloud-starter-zookeeper-discovery")

        val stream = Stream

        object Stream : IsNotADependency {
            val stream get() = module("spring-cloud-stream")

            val binderKafka get() = module("spring-cloud-stream-binder-kafka")

            val binderKafkaStreams =
                module("spring-cloud-stream-binder-kafka-streams")
        }

        val streamBinderRabbit get() = module("spring-cloud-stream-binder-rabbit")
    }

    val integration = Integration

    object Integration : DependencyGroup("org.springframework.integration", usePlatformConstraints = true) {

        val amqp get() = module("spring-integration-amqp")

        val gemfire get() = module("spring-integration-gemfire")

        val jdbc get() = module("spring-integration-jdbc")

        val jms get() = module("spring-integration-jms")

        val jpa get() = module("spring-integration-jpa")

        val kafka get() = module("spring-integration-kafka")

        val mail get() = module("spring-integration-mail")

        val mongodb get() = module("spring-integration-mongodb")

        val r2dbc get() = module("spring-integration-r2dbc")

        val redis get() = module("spring-integration-redis")

        val rsocket get() = module("spring-integration-rsocket")

        val security get() = module("spring-integration-security")

        val stomp get() = module("spring-integration-stomp")

        val test get() = module("spring-integration-test")

        val webflux get() = module("spring-integration-webflux")

        val websocket get() = module("spring-integration-websocket")

        val ws get() = module("spring-integration-ws")
    }

    val security = Security

    object Security : DependencyGroup("org.springframework.security", usePlatformConstraints = true) {
        val spring_security_messaging get() = module("spring-security-messaging")

        val spring_security_rsocket get() = module("spring-security-rsocket")

        val spring_security_test get() = module("spring-security-test")
    }

    val session = Session

    object Session : DependencyGroup("org.springframework.session", usePlatformConstraints = true) {
        val dataRedis get() = module("spring-session-data-redis")

        val jdbc get() = module("spring-session-jdbc")
    }

}
