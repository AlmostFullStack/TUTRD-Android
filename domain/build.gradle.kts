plugins {
    alias(libs.plugins.tutrd.android.library)
}

android {
    namespace = "com.afs.tutrd.domain"
}

dependencies {
    implementation(libs.javax.inject)

    implementation(projects.core.model)
}