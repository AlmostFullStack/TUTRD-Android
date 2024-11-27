plugins {
    alias(libs.plugins.tutrd.android.library)
    alias(libs.plugins.tutrd.android.hilt)
    alias(libs.plugins.tutrd.android.application.test)
    alias(libs.plugins.tutrd.android.application.firebase)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.afs.tutrd.data"
}

dependencies {

    implementation(projects.core.common)
    implementation(projects.core.local)
    implementation(projects.core.model)
    implementation(projects.core.network)
    implementation(projects.domain)

    implementation(libs.kotlinx.serialization.json)
}