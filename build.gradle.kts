plugins {
    kotlin("js") version "1.7.10"
}

group = "me.altma"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val kotlinWrappers = "org.jetbrains.kotlin-wrappers"
val kotlinWrappersVersion = "1.0.0-pre.391"

dependencies {
    testImplementation(kotlin("test"))
    implementation(enforcedPlatform("org.jetbrains.kotlin-wrappers:kotlin-wrappers-bom:$kotlinWrappersVersion"))
    implementation("$kotlinWrappers:kotlin-emotion")
    implementation("$kotlinWrappers:kotlin-react")
    implementation("$kotlinWrappers:kotlin-react-dom")
    implementation("$kotlinWrappers:kotlin-react-router-dom")
}

kotlin {
    js(IR) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
    }
}