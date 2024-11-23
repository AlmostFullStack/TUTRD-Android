plugins {
    alias(libs.plugins.tutrd.android.library)
    alias(libs.plugins.tutrd.android.library.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.afs.tutrd.core.model"
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
}