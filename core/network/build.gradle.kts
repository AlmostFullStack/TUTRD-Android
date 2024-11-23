import java.util.Properties

plugins {
    alias(libs.plugins.tutrd.android.library)
    alias(libs.plugins.tutrd.android.hilt)
}

val localProperties = Properties()
localProperties.load(project.rootProject.file("local.properties").inputStream())

android {
    namespace = "com.afs.tutrd.network"
    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", localProperties["base.url.dev"].toString())
            resValue("string", "BASE_URL", localProperties["base.url.dev"].toString())
        }
        release {
            buildConfigField("String", "BASE_URL", localProperties["base.url.prod"].toString())
            resValue("string", "BASE_URL", localProperties["base.url.dev"].toString())
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    implementation(projects.core.common)
    implementation(projects.core.local)

    implementation(libs.retrofit.core)
    implementation(libs.retrofit.moshi.converter)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.moshi.kotlin)
    ksp(libs.moshi.kotlin.codegen)
}