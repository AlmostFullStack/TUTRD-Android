plugins {
    alias(libs.plugins.tutrd.android.library)
    alias(libs.plugins.tutrd.android.library.compose)
    alias(libs.plugins.tutrd.android.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.afs.tutrd.core.navigation"
}

dependencies {
    implementation(libs.androidx.navigation.compose)
}