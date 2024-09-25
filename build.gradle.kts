import java.util.Properties

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.google.devtools.ksp) apply false
    alias(libs.plugins.google.dagger.hilt.android) apply false
    alias(libs.plugins.google.firebase.crashlytics) apply false
}

// Load version management file
val versionProperties = Properties()
file("$rootDir/version.properties").inputStream().use { versionProperties.load(it) }

val major: String = versionProperties.getProperty("MAJOR") ?: "0"
val minor: String = versionProperties.getProperty("MINOR") ?: "0"
val patch: String = versionProperties.getProperty("PATCH") ?: "0"

versionProperties.setProperty("RELEASE_VERSION", "$major.$minor.$patch")

// Initialize variables as extra properties
extra["libraryGroupId"] = versionProperties.getProperty("GROUP_ID") ?: "default.group"
extra["libraryReleaseVersion"] = versionProperties.getProperty("RELEASE_VERSION") ?: "0.0.0"

// Clean task
tasks.register<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}