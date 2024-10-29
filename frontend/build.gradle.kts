plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.jetbrains.plugin.compose)
    alias(libs.plugins.jetbrains.compose)
}

kotlin {
    js {
        browser {
            useCommonJs()
            commonWebpackConfig {
                outputFileName = "frontend.js"
                cssSupport {
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
        jsMain.dependencies {
            implementation(project(":shared"))
            implementation(compose.runtime)
            implementation(compose.html.core)
        }

        jsTest.dependencies {

        }
    }
}


