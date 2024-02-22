plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp") version "1.9.21-1.0.15"
}

android {
    namespace = "io.schiar.mochannel"
    compileSdk = 34

    defaultConfig {
        applicationId = "io.schiar.mochannel"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.6"
    }
}

dependencies {
    // androidx
    implementation("androidx.annotation:annotation-experimental:1.4.0")
    implementation("androidx.annotation:annotation:1.7.1")
    //noinspection KtxExtensionAvailable
    implementation("androidx.activity:activity:1.8.2")

    // Material 3
    implementation("androidx.compose.material3:material3-android:1.2.0")

    // ExoPlayer Media
    val mediaVersion = "1.2.1"
    implementation("androidx.media3:media3-exoplayer:$mediaVersion")
    implementation("androidx.media3:media3-common:$mediaVersion")
    implementation("androidx.media3:media3-ui:$mediaVersion")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")
    runtimeOnly("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
    //noinspection KtxExtensionAvailable
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.7.0")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")

    // Retrofit with Gson
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Gson
    implementation("com.google.code.gson:gson:2.10")

    // Guava
    implementation("com.google.guava:guava:31.1-jre")

    // Navigation
    val navigationVersion = "2.7.7"
    //noinspection KtxExtensionAvailable
    implementation("androidx.navigation:navigation-runtime:$navigationVersion")
    implementation("androidx.navigation:navigation-compose:$navigationVersion")
    implementation("androidx.navigation:navigation-common:$navigationVersion")

    // Compose
    val composeVersion = "1.6.2"
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.compose.animation:animation:$composeVersion")
    implementation("androidx.compose.foundation:foundation-layout:$composeVersion")
    implementation("androidx.compose.foundation:foundation:$composeVersion")
    implementation("androidx.compose.runtime:runtime:$composeVersion")
    implementation("androidx.compose.ui:ui-graphics:$composeVersion")
    implementation("androidx.compose.ui:ui-text:$composeVersion")
    implementation("androidx.compose.ui:ui-unit:$composeVersion")
    implementation("androidx.compose.ui:ui:$composeVersion")

    // Compose Preview
    implementation("androidx.compose.ui:ui-tooling-preview-android:$composeVersion")

    // Compose for TV
    implementation("androidx.tv:tv-material:1.0.0-alpha10")
    implementation("androidx.tv:tv-foundation:1.0.0-alpha10")

    // Room for database persistence
    val roomVersion = "2.6.1"
    implementation("androidx.room:room-runtime:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    implementation("androidx.room:room-common:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")

    // SQLite
    //noinspection KtxExtensionAvailable
    implementation("androidx.sqlite:sqlite:2.4.0")
}