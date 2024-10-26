import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.sqldelight)
    alias(libs.plugins.ksp)
    alias(libs.plugins.mokkery)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.compose.compiler)
}

kotlin {
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
            export(libs.kmp.notifier)

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
        implementation(libs.sqldelight.runtime)
        implementation(libs.kamel)
        implementation(libs.shimmer)
        implementation(libs.settings)
        implementation(projects.uikit)

        api(libs.kotlinx.coroutines.core)
        api(libs.kotlinx.coroutines.test)
        api(libs.kmp.notifier) // in iOS export this library

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
        implementation(libs.androidx.activity.compose)
        implementation(libs.ktor.client.okhttp)
        implementation(libs.sqldelight.android)
        implementation(libs.koin.android)
        implementation(libs.accompanist.permissions)

        implementation(project.dependencies.platform(libs.firebase.bom))
        implementation(libs.firebase.messaging)
    }


    sourceSets.iosMain.dependencies {
        implementation(libs.ktor.client.darwin)
        implementation(libs.sqldelight.native)
        implementation(libs.touchlab)
    }

    sourceSets {
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.ktor.client.okhttp)
                implementation(libs.sqldelight.sqlite)
                implementation(libs.kotlinx.coroutines.swing)
                implementation(libs.calf.filepicker)
            }
        }
    }

    sourceSets.commonTest.dependencies {
        implementation(libs.kotlin.test)
        implementation(kotlin("test-annotations-common"))
        implementation(libs.assertk)
        implementation(libs.ktor.client.mock)
        implementation(libs.koin.test)
    }

    val androidTest = sourceSets.getByName("androidUnitTest") {
        dependencies {
            implementation(kotlin("test-junit"))
            implementation(libs.junit)
            implementation(libs.sqldelight.sqlite)
        }
    }
}

configurations.all {
    resolutionStrategy {
        force("androidx.compose.material:material-ripple:1.7.0-alpha05")
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
    dependencies {}
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