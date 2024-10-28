package com.afs.tutrd.buildlogic.convention

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.ProductFlavor


@Suppress("EnumEntryName")
enum class TutrdFlavor(val appLabel: String, val applicationIdSuffix: String? = null) {
    prod(appLabel = "튜터드"),
    dev(appLabel = "튜터드 dev", applicationIdSuffix = ".dev")
}

fun configureFlavors(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
    flavorConfigurationBlock: ProductFlavor.(flavor: TutrdFlavor) -> Unit = {}
) {
    commonExtension.apply {
        flavorDimensions += "build-type"
        productFlavors {
            TutrdFlavor.values().forEach { flavor ->
                create(flavor.name) {
                    dimension = "build-type"
                    flavorConfigurationBlock(this, flavor)
                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        if (flavor.applicationIdSuffix != null) {
                            applicationIdSuffix = flavor.applicationIdSuffix
                        }
                    }
                    manifestPlaceholders["appLabel"] = flavor.appLabel
                }
            }
        }
    }
}