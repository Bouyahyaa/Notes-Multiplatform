import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

val NamedDomainObjectContainer<KotlinSourceSet>.mobileMain: NamedDomainObjectProvider<KotlinSourceSet>
    get() = named("mobileMain")

val NamedDomainObjectContainer<KotlinSourceSet>.desktopMain: NamedDomainObjectProvider<KotlinSourceSet>
    get() = named("desktopMain")


plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.sqldelight)
}

kotlin {
    @OptIn(ExperimentalKotlinGradlePluginApi::class)
    applyDefaultHierarchyTemplate {
        common {
            group("mobile") {
                withIos()
                withAndroidTarget()
            }

            group("desktop") {
                withJvm()
            }
        }
    }


    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    jvm("desktop")

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = false
        }
    }

    sourceSets.commonMain.dependencies {
        implementation(compose.runtime)
        implementation(compose.foundation)
        implementation(compose.material)
        implementation(compose.ui)
        implementation(compose.components.resources)
        implementation(compose.material3)
        implementation(libs.compose.navigation)
        implementation(libs.compose.viewmodel)
        implementation(libs.screen.size)
        implementation(libs.kotlin.coroutine)
        implementation(libs.sqldelight.runtime)
        implementation(libs.kamel)
        implementation(libs.shimmer)
        implementation(libs.settings)
        implementation(projects.uikit)

        with(libs.ktor) {
            implementation(core)
            implementation(content.negotiation)
            implementation(serialization)
        }
        with(libs.koin) {
            implementation(core)
            implementation(compose)
        }
    }

    sourceSets.androidMain.dependencies {
        implementation(libs.compose.ui.tooling.preview)
        implementation(libs.androidx.activity.compose)
        implementation(libs.ktor.client.okhttp)
        implementation(libs.sqldelight.android)
        implementation(libs.koin.android)
    }

    sourceSets {
        val mobileMain by getting {
            dependencies {
                implementation("network.chaintech:compose-multiplatform-media-player:1.0.5")
            }
        }

        val iosMain by getting {
            dependsOn(mobileMain)
            dependencies {
                implementation(libs.ktor.client.darwin)
                implementation(libs.sqldelight.native)
                implementation(libs.touchlab)
            }
        }
    }

    sourceSets.desktopMain.dependencies {
        implementation(compose.desktop.currentOs)
        implementation(libs.ktor.client.okhttp)
        implementation(libs.sqldelight.sqlite)
        implementation(libs.kotlinx.coroutines.swing)
        implementation("uk.co.caprica:vlcj:4.7.0")
    }
}

android {
    namespace = "com.bouyahya.notes"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.bouyahya.notes"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dependencies {
        debugImplementation(libs.compose.ui.tooling)
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "com.bouyahya.notes"
            packageVersion = "1.0.0"
        }
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.bouyahya.notes")
        }
    }
}