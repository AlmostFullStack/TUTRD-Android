
import com.easyhz.noffice.build_logic.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("tutrd.android.library")
                apply("tutrd.android.hilt")
            }

            dependencies {
                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
            }
        }
    }
}