plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
    kotlin("plugin.serialization") version "2.1.10"
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "org.wahid.borutoappversion1"
    compileSdk = 35

    defaultConfig {
        applicationId = "org.wahid.borutoappversion1"
        minSdk = 25
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    //Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.paging)


//    // DataStore Preferences
    implementation(libs.androidx.datastore.preferences)




//    //Navigation Component
    implementation(libs.androidx.navigation.compose)
//    // Coil
    implementation(libs.coil.network.okhttp)
    implementation(libs.coil.compose)


//    implementation (libs.androidx.room.runtime)
//    implementation(libs.androidx.room.compiler)
//    implementation (libs.androidx.room.ktx)
//

//    implementation(libs.androidx.navigation.compose)
//
//
//    // Retrofit
    implementation (libs.retrofit)
    implementation (libs.gson)
    implementation(libs.kotlinx.serialization.json)
//    implementation (libs.converter.kotlinx.serialization)
    implementation(libs.converter.gson)
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.3")





//    // Paging 3.0
    implementation(libs.androidx.paging.runtime.ktx)
    implementation(libs.androidx.paging.compose)


//    // Dagger - Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)


//    // Swipe to Refresh - Accompanist
    implementation(libs.androidx.swiperefreshlayout)

    implementation(libs.androidx.viewpager2)
//    implementation(libs.androidx.swiperefreshlayout)
//    // System UI Controller - Accompanist
//
//    // Palette API
    implementation(libs.androidx.palette.ktx)

}