import de.fayard.refreshVersions.core.internal.DependencyGroup
import org.gradle.kotlin.dsl.IsNotADependency

object Spring : IsNotADependency {

    val boms = Boms

    object Boms: IsNotADependency {
        val solace = "com.solace.spring.cloud:solace-spring-cloud-bom:_"
        val vaadin = "com.vaadin:vaadin-bom:_"
        val geode = "org.springframework.geode:spring-geode-bom:_"
        val wavefront = "com.wavefront:wavefront-spring-boot-bom:_"
        val testcontainers = "org.testcontainers:testcontainers-bom:_"
        val codecentric = "de.codecentric:spring-boot-admin-dependencies:_"
        val springCloud = "io.pivotal.spring.cloud:spring-cloud-services-dependencies:_"
        val springAzure = "com.azure.spring:azure-spring-boot-bom:_"
        val springclmoud = "org.springframework.cloud:spring-cloud-dependencies:_"
    }

    val lombok = "org.projectlombok:lombok"

    val thymeleaf_extras_springsecurity5 = "org.thymeleaf.extras:thymeleaf-extras-springsecurity5"

    val kafka = "org.springframework.kafka:spring-kafka"

    val kafkaTest = "org.springframework.kafka:spring-kafka-test"

    val springRestdocsWebtestclient = "org.springframework.restdocs:spring-restdocs-webtestclient"

    val geode = "org.springframework.geode:spring-geode-starter"

    val jacksonModuleKotlin = "com.fasterxml.jackson.module:jackson-module-kotlin"

    val h2 = "com.h2database:h2"

    val jcc = "com.ibm.db2:jcc"

    val mssqlJdbc = "com.microsoft.sqlserver:mssql-jdbc"

    val okta = "com.okta.spring:okta-spring-boot-starter:_"

    val ojdbc8 = "com.oracle.database.jdbc:ojdbc8"

    val solaceStream = "com.solace.spring.cloud:spring-cloud-starter-stream-solace"

    val unboundidLdapsdk = "com.unboundid:unboundid-ldapsdk"

    val vadinGradlePlugin = "com.vaadin:com.vaadin.gradle.plugin:_"

    val vaadinStarter = "com.vaadin:vaadin-spring-boot-starter"

    val wavefront = "com.wavefront:wavefront-spring-boot-starter"

    val embedMongo = "de.flapdoodle.embed:de.flapdoodle.embed.mongo"

    val r2DbcMysql = "dev.miku:r2dbc-mysql"


    val dependencyManagementPlugin =
        "io.spring.dependency-management:io.spring.dependency-management.gradle.plugin:_"

    val mysqlConnectorJava = "mysql:mysql-connector-java"

    val camelSpringBootStarter = "org.apache.camel.springboot:camel-spring-boot-starter:_"

    val derby = "org.apache.derby:derby"

    val kafkaStreams = "org.apache.kafka:kafka-streams"

    val asciidoctorPlugin =
        "org.asciidoctor.convert:org.asciidoctor.convert.gradle.plugin:_"

    val flywayCore = "org.flywaydb:flyway-core"

    val hsqldb = "org.hsqldb:hsqldb"

    val liquibaseCore = "org.liquibase:liquibase-core"

    val mariadb = "org.mariadb.jdbc:mariadb-java-client"

    val r2DbcMariadb = "org.mariadb:r2dbc-mariadb"

    val mybatis = "org.mybatis.spring.boot:mybatis-spring-boot-starter:_"

    val postgresql = "org.postgresql:postgresql"

    val rabbitTest = "org.springframework.amqp:spring-rabbit-test"

    val batchTest = "org.springframework.batch:spring-batch-test"

    val springBootPlugin =
        "org.springframework.boot:org.springframework.boot.gradle.plugin:_"

    val reactor = Reactor

    object Reactor : DependencyGroup("io.projectreactor") {
        val kotlin = "io.projectreactor.kotlin:reactor-kotlin-extensions"

        val test = "io.projectreactor:reactor-test"
    }

    val data = Data

    object Data : DependencyGroup("org.framework.data") {
        val halExplorer = "org.springframework.data:spring-data-rest-hal-explorer"
    }

    val azure = Azure

    object Azure : DependencyGroup(group = "com.azure.spring", usePlatformConstraints = true) {

        val starter = module("azure-spring-boot-starter")

        val activeDirectory = module("azure-spring-boot-starter-active-directory")

        val keyvaultSecrets = module("azure-spring-boot-starter-keyvault-secrets")

        val starterStorage = module("azure-spring-boot-starter-storage")
    }

    val codeCentric = CodeCentric

    object CodeCentric : DependencyGroup("de.codecentric", usePlatformConstraints = true) {
        val clientStarter = module("spring-boot-admin-starter-client")
        val serverStarter = module("spring-boot-admin-starter-server")
    }

    val micrometer = MicroMeter

    object MicroMeter : DependencyGroup("io.micrometer", usePlatformConstraints = true) {
        val datadog = module("micrometer-registry-datadog")

        val graphite = module("micrometer-registry-graphite")

        val influx = module("micrometer-registry-influx")

        val newRelic = module("micrometer-registry-new-relic")

        val prometheus = module("micrometer-registry-prometheus")
    }

    val springCloud = SpringCloud

    object SpringCloud : DependencyGroup("io.pivotal.spring.cloud", usePlatformConstraints = true) {
        val circuitBreaker = module("spring-cloud-services-starter-circuit-breaker")

        val configClient = module("spring-cloud-services-starter-config-client")

        val serviceRegistry = module("spring-cloud-services-starter-service-registry")
    }

    val r2dbc = R2dbc

    object R2dbc : DependencyGroup("io.r2dbc", usePlatformConstraints = true) {
        val h2 = module("r2dbc-h2")

        val mssql = module("r2dbc-mssql")

        val ostgresql = module("r2dbc-postgresql")
    }

    val boot = Boot

    object Boot : DependencyGroup("org.springframework.boot", usePlatformConstraints = true) {
        val devTools = "org.springframework.boot:spring-boot-devtools"

        val configurationProcessor = "org.springframework.boot:spring-boot-configuration-processor"

        val activemq = module("spring-boot-starter-activemq")

        val actuator = module("spring-boot-starter-actuator")

        val amqp = module("spring-boot-starter-amqp")

        val artemis = module("spring-boot-starter-artemis")

        val batch = module("spring-boot-starter-batch")

        val cache = module("spring-boot-starter-cache")

        val data = Data

        object Data : IsNotADependency {
            val cassandra = module("spring-boot-starter-data-cassandra")

            val cassandraReactive =
                module("spring-boot-starter-data-cassandra-reactive")

            val couchbase = module("spring-boot-starter-data-couchbase")

            val couchbase_reactive =
                module("spring-boot-starter-data-couchbase-reactive")

            val elasticsearch =
                module("spring-boot-starter-data-elasticsearch")

            val jdbc = module("spring-boot-starter-data-jdbc")

            val jpa = module("spring-boot-starter-data-jpa")

            val ldap = module("spring-boot-starter-data-ldap")

            val mongodb = module("spring-boot-starter-data-mongodb")

            val mongodbReactive =
                module("spring-boot-starter-data-mongodb-reactive")

            val neo4j = module("spring-boot-starter-data-neo4j")

            val r2dbc = module("spring-boot-starter-data-r2dbc")

            val redis = module("spring-boot-starter-data-redis")

            val redis_reactive =
                module("spring-boot-starter-data-redis-reactive")

            val rest = module("spring-boot-starter-data-rest")

            val solr = module("spring-boot-starter-data-solr")
        }

        val freemarker = module("spring-boot-starter-freemarker")

        val groovyTemplates = module("spring-boot-starter-groovy-templates")

        val hateoas = module("spring-boot-starter-hateoas")

        val integration = module("spring-boot-starter-integration")

        val jdbc = module("spring-boot-starter-jdbc")

        val jersey = module("spring-boot-starter-jersey")

        val jooq = module("spring-boot-starter-jooq")

        val mail = module("spring-boot-starter-mail")

        val mustache = module("spring-boot-starter-mustache")

        val oauth2Client = module("spring-boot-starter-oauth2-client")

        val oauth2ResourceServer =
            module("spring-boot-starter-oauth2-resource-server")

        val quartz = module("spring-boot-starter-quartz")

        val rsocket = module("spring-boot-starter-rsocket")

        val security = module("spring-boot-starter-security")

        val test = module("spring-boot-starter-test")

        val thymeleaf = module("spring-boot-starter-thymeleaf")

        val validation = module("spring-boot-starter-validation")

        val webServices = module("spring-boot-starter-web-services")

        val webflux = module("spring-boot-starter-webflux")

        val websocket = module("spring-boot-starter-websocket")
    }


    val cloud = Cloud

    object Cloud : DependencyGroup("org.springframework.cloud", usePlatformConstraints = true) {

        val bus = module("spring-cloud-bus")

        val cloudfoundry_discovery =
            module("spring-cloud-cloudfoundry-discovery")

        val config_server = module("spring-cloud-config-server")

        val function_web = module("spring-cloud-function-web")

        val gcp_starter = module("spring-cloud-gcp-starter")

        val gcp_starter_pubsub = module("spring-cloud-gcp-starter-pubsub")

        val gcp_starter_storage = module("spring-cloud-gcp-starter-storage")

        val starter = module("spring-cloud-starter")

        val aws = Aws

        object Aws : IsNotADependency {
            val aws = module("spring-cloud-starter-aws")

            val jdbc = module("spring-cloud-starter-aws-jdbc")

            val messaging = module("spring-cloud-starter-aws-messaging")
        }

        val circuitbreakerReactorResilience4J =
            module("spring-cloud-starter-circuitbreaker-reactor-resilience4j")

        val config = module("spring-cloud-starter-config")

        val consulConfig = module("spring-cloud-starter-consul-config")

        val consulDiscovery =
            module("spring-cloud-starter-consul-discovery")

        val contractStubRunner =
            module("spring-cloud-starter-contract-stub-runner")

        val contractVerifier =
            module("spring-cloud-starter-contract-verifier")

        val gateway = module("spring-cloud-starter-gateway")

        val loadbalancer = module("spring-cloud-starter-loadbalancer")
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

            val zuul = module("spring-cloud-starter-netflix-zuul")
        }

        val oauth2 = module("spring-cloud-starter-oauth2")

        val openServiceBroker =
            module("spring-cloud-starter-open-service-broker")

        val openfeign = module("spring-cloud-starter-openfeign")

        val security = module("spring-cloud-starter-security")

        val sleuth = module("spring-cloud-starter-sleuth")

        val task = module("spring-cloud-starter-task")

        val vault_config = module("spring-cloud-starter-vault-config")

        val zipkin = module("spring-cloud-starter-zipkin")

        val zookeeperConfig =
            module("spring-cloud-starter-zookeeper-config")

        val zookeeperDiscovery =
            module("spring-cloud-starter-zookeeper-discovery")

        val stream = Stream

        object Stream : IsNotADependency {
            val stream = module("spring-cloud-stream")

            val binderKafka = module("spring-cloud-stream-binder-kafka")

            val binderKafkaStreams =
                module("spring-cloud-stream-binder-kafka-streams")
        }

        val streamBinderRabbit = module("spring-cloud-stream-binder-rabbit")
    }



    val integration = Integration

    object Integration : DependencyGroup("org.springframework.integration", usePlatformConstraints = true) {

        val amqp = module("spring-integration-amqp")

        val gemfire = module("spring-integration-gemfire")

        val jdbc = module("spring-integration-jdbc")

        val jms = module("spring-integration-jms")

        val jpa = module("spring-integration-jpa")

        val kafka = module("spring-integration-kafka")

        val mail = module("spring-integration-mail")

        val mongodb = module("spring-integration-mongodb")

        val r2dbc = module("spring-integration-r2dbc")

        val redis = module("spring-integration-redis")

        val rsocket = module("spring-integration-rsocket")

        val security = module("spring-integration-security")

        val stomp = module("spring-integration-stomp")

        val test = module("spring-integration-test")

        val webflux = module("spring-integration-webflux")

        val websocket = module("spring-integration-websocket")

        val ws = module("spring-integration-ws")
    }

    val security = Security

    object Security : DependencyGroup("org.springframework.security", usePlatformConstraints = true) {
        val spring_security_messaging = module("spring-security-messaging")

        val spring_security_rsocket = module("spring-security-rsocket")

        val spring_security_test = module("spring-security-test")
    }

    val session = Session

    object Session : DependencyGroup("org.springframework.session", usePlatformConstraints = true) {
        val dataRedis = module("spring-session-data-redis")

        val jdbc = module("spring-session-jdbc")
    }

    val testContainers = TestContainers

    object TestContainers : DependencyGroup("org.testcontainers") {
        val cassandra = module("cassandra")

        val couchbase = module("couchbase")

        val db2 = module("db2")

        val elasticsearch = module("elasticsearch")

        val junit_jupiter = module("junit-jupiter")

        val kafka = module("kafka")

        val mariadb = module("mariadb")

        val mongodb = module("mongodb")

        val mssqlserver = module("mssqlserver")

        val mysql = module("mysql")

        val neo4j = module("neo4j")

        val oracle_xe = module("oracle-xe")

        val postgresql = module("postgresql")

        val r2dbc = module("r2dbc")

        val rabbitmq = module("rabbitmq")

        val solr = module("solr")
    }
}
