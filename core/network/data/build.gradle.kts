plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.google.dagger.hilt.android)
    id("maven-publish")
}

android {
    namespace = "com.moataz.core.network.data"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.espresso.core)
    api(project(":core:network:domain"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    // firebase storage Lib
    implementation(libs.firebase.storage)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.coil.compose)

    //Dependency Injection
    implementation(libs.androidx.hilt.navigation)
    implementation(libs.dagger.hilt)
    ksp(libs.dagger.hilt.compiler)

    //Room Database
    implementation(libs.androidx.room.common)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

//Import okHttp dependencies
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)

//Import Retrofit dependencies
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
}

// Accessing properties set in the root build.gradle.kts
val libraryGroupId: String by project
val libraryReleaseVersion: String by project

// Publishing configuration
publishing {
    publications{
        val libraryArtifactId = "data"
        create<MavenPublication>("library") {
            groupId = libraryGroupId
            artifactId = libraryArtifactId
            version = libraryReleaseVersion
            artifact("${layout.buildDirectory.get()}/outputs/aar/${artifactId}-release.aar")
        }
    }

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/moatazhamada/bug-it-core")
            credentials {
                username = System.getenv("USER") ?: ""
                password = System.getenv("TOKEN") ?: ""
            }
        }
    }
}