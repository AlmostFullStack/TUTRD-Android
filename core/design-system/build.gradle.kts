plugins {
    alias(libs.plugins.tutrd.android.library)
    alias(libs.plugins.tutrd.android.library.compose)
    alias(libs.plugins.tutrd.android.application.test)
}

android {
    namespace = "com.afs.tutrd.core.design_system"
}

dependencies {
    implementation(projects.core.model)
    implementation(libs.androidx.foundation.layout.android)
    coreLibraryDesugaring(libs.desugaring)
    implementation(libs.calendar.compose)
}