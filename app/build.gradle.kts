plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.cameraxsample"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cameraxsample"
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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("androidx.camera:camera-core:1.1.0-beta01")
    implementation("androidx.camera:camera-camera2:1.1.0-beta01")
    implementation("androidx.camera:camera-lifecycle:1.1.0-beta01")
    implementation("androidx.camera:camera-video:1.1.0-beta01")
    implementation("androidx.camera:camera-view:1.1.0-beta01")
    implementation("androidx.camera:camera-extensions:1.1.0-beta01")

    // CropView
    implementation("com.vanniktech:android-image-cropper:4.5.0")
    // TextRegonation Google Lib
    // implementation("com.google.android.gms:play-services-mlkit-text-recognition:16.1.3")
    implementation("com.google.mlkit:text-recognition-korean:16.0.0")
    // GPS
    implementation("com.google.android.gms:play-services-location:21.0.1")
    implementation("com.naver.maps:map-sdk:3.17.0")
    // Permission
    implementation("io.github.ParkSangGwon:tedpermission:2.3.0")
    // Zxing barcode qrcode lib
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation("com.google.zxing:core:3.3.0")
    // Google barcode qrcode lib
    implementation("com.google.android.gms:play-services-code-scanner:16.1.0")

}