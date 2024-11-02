pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "TUTRD"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":feature:ui-classroom")
include(":feature:ui-payment")
include(":feature:ui-profile")
include(":feature:ui-schedule")
include(":feature:ui-session")
include(":feature:ui-sign")
include(":core:design-system")
include(":core:navigation")
include(":core:model")
include(":domain")
include(":data")
