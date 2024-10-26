import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

val NamedDomainObjectContainer<KotlinSourceSet>.mobileMain: NamedDomainObjectProvider<KotlinSourceSet>
    get() = named("mobileMain")

val NamedDomainObjectContainer<KotlinSourceSet>.desktopMain: NamedDomainObjectProvider<KotlinSourceSet>
    get() = named("desktopMain")


plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.compose.compiler)
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

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.material3)
        }
    }

    sourceSets {
        val mobileMain by getting {
            dependencies {
                implementation(libs.chaintech.player)
            }
        }

        val iosMain by getting {
            dependsOn(mobileMain)
        }
    }
}

android {
    namespace = "com.bouyahya.notes"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
    sourceSets.getAt("main").resources.srcDirs("src/commonMain/resources")
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    dependencies {}
}