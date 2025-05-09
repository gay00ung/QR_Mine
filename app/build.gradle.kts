plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "net.ifmain.qr_mine"
    compileSdk = 35

    defaultConfig {
        applicationId = "net.ifmain.qr_mine"
        minSdk = 26
        targetSdk = 35
        versionCode = 2
        versionName = "1.1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    signingConfigs {
        register("release") {
            storeFile = file("android-key.keystore")
            storePassword = project.findProperty("KEYSTORE_PASSWORD")?.toString()
            keyAlias = project.findProperty("KEY_ALIAS")?.toString()
            keyPassword = project.findProperty("KEY_PASSWORD")?.toString()
            enableV4Signing = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs["release"]
            isDebuggable = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // QR Library
    implementation (libs.zxing.android.embedded)

    // Lottie
    implementation (libs.lottie.compose)
}

androidComponents {
    onVariants { variant ->
        val versionName = android.defaultConfig.versionName
        variant.outputs.forEach { output ->
            if (output is com.android.build.api.variant.impl.VariantOutputImpl) {
                output.outputFileName = "qrmine_${versionName}.apk"
            }
        }

        tasks.configureEach {
            if (name == "bundle${variant.name.capitalize()}") {
                doLast {
                    val aabDir = file("${buildDir}/outputs/bundle/${variant.name}/")
                    val aabFile = aabDir.listFiles()?.find { it.extension == "aab" }

                    if (aabFile != null) {
                        val newAabFile = File(aabDir, "qrmine_${versionName}.aab")
                        aabFile.renameTo(newAabFile)
                        println("✅ AAB renamed to: ${newAabFile.name}")
                    } else {
                        println("⚠️ No AAB file found in ${aabDir.path}")
                    }
                }
            }
        }
    }
}