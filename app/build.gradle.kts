plugins {
    alias(libs.plugins.tutrd.android.application)
    alias(libs.plugins.tutrd.android.application.compose)
    alias(libs.plugins.tutrd.android.application.flavors)
    alias(libs.plugins.tutrd.android.application.test)
    alias(libs.plugins.tutrd.android.application.firebase)
    alias(libs.plugins.tutrd.android.hilt)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.firebase.crashlytics)
}

android {
    namespace = "com.afs.tutrd"

    defaultConfig {
        applicationId = "com.afs.tutrd"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.core.designSystem)
    implementation(projects.core.network)
    implementation(projects.feature.uiSchedule)
    implementation(projects.feature.uiSession)
    implementation(projects.feature.uiSign)
    implementation(projects.domain)
    implementation(projects.data)
}