plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = Configs.APP_NAME
    compileSdk = Configs.COMPILE_SDK

    defaultConfig {
        applicationId = Configs.APP_NAME
        minSdk = Configs.MIN_SDK
        targetSdk = Configs.TARGET_SDK
        versionCode = Configs.VERSION_CODE
        versionName = Configs.VERSION_NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":presentation"))

    testImplementation(ModuleDependency.Default.JUNIT)
    androidTestImplementation(ModuleDependency.Default.TEST_RUNNER)
    androidTestImplementation(ModuleDependency.Default.ESPRESSO_CORE)
    implementation(ModuleDependency.Hilt.HILT)
    kapt(ModuleDependency.Hilt.HILT_COMPILER)
    implementation(ModuleDependency.Default.CORE)
    implementation(ModuleDependency.Default.APP_COMPAT)
    implementation(ModuleDependency.Timber.TIMBER)
}