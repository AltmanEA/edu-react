plugins {
    kotlin("js") version "1.5.30"
}

group = "ru.altmanea"
version = "1.0-SNAPSHOT"

repositories {
    jcenter()
    mavenCentral()
}

fun kotlinw(target: String): String =
    "org.jetbrains.kotlin-wrappers:kotlin-$target"

val kotlinWrappersVersion = "0.0.1-pre.242-kotlin-1.5.30"

dependencies {
    testImplementation(kotlin("test", "1.5.30"))
    implementation(enforcedPlatform(kotlinw("wrappers-bom:${kotlinWrappersVersion}")))
    implementation(kotlinw("react"))
    implementation(kotlinw("react-dom"))
    implementation(kotlinw("styled"))
    implementation(kotlinw("react-router-dom"))
    implementation(kotlinw("redux"))
    implementation(kotlinw("react-redux"))
}

kotlin {
    js(LEGACY) {
        binaries.executable()
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
            testTask {
                useKarma {
                    useChromeHeadless()
                }
            }
        }
    }
}