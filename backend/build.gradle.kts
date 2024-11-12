plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ktor)
    application
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

kotlin {
    jvmToolchain(21)
    jvm {
        withJava()
    }

    sourceSets {
        jvmMain.dependencies {
            implementation(project(":shared"))
            implementation(libs.ktor.server.core)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.server.content.negotiation)
            implementation(libs.exposed.core)
            implementation(libs.exposed.jdbc)
            implementation(libs.h2)
            implementation(libs.ktor.server.call.logging)
            implementation(libs.ktor.server.cors)
            implementation(libs.ktor.server.host.common)
            implementation(libs.ktor.server.status.pages)
            implementation(libs.ktor.server.netty)
            implementation(libs.logback.classic)
            implementation(libs.postgresql)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.auth)
            implementation(libs.ktor.client.cio)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.russhwolf.multiplatform.settings)
        }

        jvmTest.dependencies {
            implementation(libs.ktor.server.test.host)
            implementation(libs.kotlin.test)
        }
    }
}

// for full-stack
tasks.named<Copy>("jvmProcessResources") {
    val jsBrowserDistribution = project(":frontend").tasks.named("jsBrowserDistribution") //"jsBrowserDevelopmentExecutableDistribution"
    dependsOn(jsBrowserDistribution)
    from(jsBrowserDistribution) {
        include("*.js")
        include("*.js.map")
        include("*.html")
    }
}