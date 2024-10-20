
plugins {
    application
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ktor)
    alias(libs.plugins.jetbrains.plugin.compose)
    alias(libs.plugins.jetbrains.compose)
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    google()
}

kotlin {
    jvmToolchain(21)
    jvm {
        withJava()
    }
    js {
        browser {
            useCommonJs()
            commonWebpackConfig {
                outputFileName = "frontend.js"
                cssSupport{
                    enabled = true
                }
                scssSupport {
                    enabled = true
                }
            }
            binaries.executable()
        }
    }
    sourceSets {

        commonMain {
            dependencies {
                implementation(libs.ktor.serialization.kotlinx.json)
                implementation(compose.runtime)
            }
        }

        jvmMain {
            dependencies {
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
            }
        }

        jvmTest {
            dependencies {
                implementation(libs.ktor.server.test.host)
                implementation(libs.kotlin.test.junit)
            }
        }

        jsMain {
            dependencies {
                implementation(compose.html.core)
            }
        }
    }
}

// for full-stack
tasks.named<Copy>("jvmProcessResources") {
    val jsBrowserDistribution = tasks.named("jsBrowserDistribution") //"jsBrowserDevelopmentExecutableDistribution"
    dependsOn(jsBrowserDistribution)
    from(jsBrowserDistribution){
        include("*.js")
        include("*.js.map")
        include("*.html")
    }
}
