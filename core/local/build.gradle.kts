plugins {
    alias(libs.plugins.tutrd.android.library)
    alias(libs.plugins.tutrd.android.hilt)
}

android {
    namespace = "com.afs.tutrd.local"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.model)

    implementation(libs.androidx.datastore)
    implementation(libs.androidx.datastore.core)
}