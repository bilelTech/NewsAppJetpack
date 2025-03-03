plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.test.newsappjetpack"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.test.newsappjetpack"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "BASE_URL", "\"https://newsapi.org/\"")
            buildConfigField("String", "API_KEY", "\"b385adec3cb0481cb05f06384b6c7860\"")
        }
        release {
            buildConfigField("String", "BASE_URL", "\"https://newsapi.org/\"")
            buildConfigField("String", "API_KEY", "\"b385adec3cb0481cb05f06384b6c7860\"")
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
        buildConfig = true
    }
}

dependencies {

    // UI
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Splash Screen API
    implementation(libs.androidx.core.splashscreen)

    //Accompanist
    implementation(libs.accompanist.systemuicontroller)

    //Dagger Hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.compiler)

    //Datastore Preferences
    implementation(libs.androidx.datastore.preferences)

    //Compose Navigation
    implementation(libs.androidx.navigation.compose)

    //retrofit for consume api
    implementation(libs.retrofit.converter.gson)
    implementation(libs.retrofit.okhttp)
    implementation(libs.retrofit)
    implementation(libs.retrofit.urlConnection)
    implementation(libs.retrofit.logging)
    implementation(libs.gson)

    // Paging compose
    implementation(libs.paging.compose)
    implementation(libs.paging.runtime)
    testImplementation(libs.paging.test)





    // Unit test & Test Ui
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

}