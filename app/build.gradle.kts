plugins {
    alias(libs.plugins.androidApplication)
}

android {
    namespace = "com.ganesh.duaremart"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ganesh.duaremart"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    viewBinding {
        enable = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // Scalable size unit (Support dor different screen size)
    implementation("com.intuit.sdp:sdp-android:1.1.0")
    implementation("com.intuit.ssp:ssp-android:1.1.0")

    // For Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    // For Gson Converter
    implementation ("com.squareup.retrofit2:converter-gson:2.4.0")

    // Picasso for load image url into image view
    implementation ("com.squareup.picasso:picasso:2.71828")
}