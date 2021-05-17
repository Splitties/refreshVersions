@file:Suppress("SpellCheckingInspection")

plugins {
    id("com.gradle.enterprise").version("3.1")
    id("de.fayard.refreshVersions") version "€{currentVersion}"
}

refreshVersions {
    extraArtifactVersionKeyRules = listOf(file("refreshVersions-extra-rules.txt").readText())
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}



include {
    "products" {
        "red" {
            "client" {
                "app-android"()
                "app-desktop"()
                "app-ios"()
                "core"()
                "database"()
            }
            "backend-api"()
            "backend" {
                "entry-point"()
                "database"()
            }
        }
        "green" {
            "client" {
                "app-android"()
                "app-desktop"()
                "app-ios"()
                "core"()
                "database"()
            }
            "backend-api"()
            "backend" {
                "entry-point"()
                "database"()
            }
        }
        "blue" {
            "client" {
                "app-android"()
                "app-wearos"()
                "app-ios"()
                "app-watchos"()
                "app-desktop"()
                "core"()
                "database"()
            }
            "backend-api"()
            "backend" {
                "entry-point"()
                "database"()
            }
        }
    }
    "extensions" {
        "common"()
        "compose"()
        "android" {
            "appcompat"()
            "material-components"()
            "paging"()
        }
    }
    "utilities" {
        "logger" {
            "client"()
            "backend"()
        }
    }
    "design-system" {
        "common-widgets"()
        "fancy-widget-one"()
        "fancy-widget-two"()
    }
    "libraries" {
        "time"()
        "xor"()
        "common-error-core"()
        "resource"()
        "platform-permissions"()
    }
    "internal-tools" {
        "stuff" {
            "something"()
            "somethingElse"()
        }
    }
    "testing" {
        "whatever"()
    }
}

//region include DSL
class ModuleParentScope(
    private val name: String,
    private val parent: ModuleParentScope? = null
) {

    operator fun String.invoke(block: (ModuleParentScope.() -> Unit)? = null) {
        check(startsWith(':').not())
        val moduleName = ":$this"
        val projectName = "$parentalPath$moduleName"
        include(projectName)
        block?.let { buildNode ->
            ModuleParentScope(
                name = moduleName,
                parent = this@ModuleParentScope
            ).buildNode()
        }
    }

    private val parentalPath: String =
        generateSequence(this) { it.parent }
            .map { it.name }.toList().reversed().joinToString("")

}

inline fun include(block: ModuleParentScope.() -> Unit) {
    ModuleParentScope("").block()
}
//endregion
