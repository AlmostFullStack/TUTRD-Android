plugins {
    alias(libs.plugins.tutrd.android.library.compose)
    alias(libs.plugins.tutrd.android.feature)
    alias(libs.plugins.tutrd.android.hilt)
    alias(libs.plugins.tutrd.android.application.test)
}

android {
    namespace = "com.afs.tutrd.feature.sign"
}

dependencies {

    implementation(projects.domain)
    implementation(projects.core.common)
    implementation(projects.core.designSystem)
    implementation(projects.core.model)
}