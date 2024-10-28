import com.android.build.api.dsl.ApplicationExtension
import com.afs.tutrd.buildlogic.convention.configureAndroidCompose
import com.afs.tutrd.buildlogic.convention.configureKotlinJvm
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.getByType

class AndroidApplicationComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")

            val extension = extensions.getByType<ApplicationExtension>()
            configureAndroidCompose(extension)
            configureKotlinJvm()
        }
    }
}