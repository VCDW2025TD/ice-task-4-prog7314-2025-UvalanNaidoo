plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    //id("com.android.application")
    id("com.google.gms.google-services") // ✅ Apply here in the app module




}

android {
    namespace = "com.example.memestream1"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.memestream1"
        minSdk = 24
        targetSdk = 36
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Lifecycle / ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.6")

    // Retrofit + Gson
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // Retrofit + Moshi

    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")
    implementation("com.squareup.moshi:moshi:1.15.1")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.15.1")

    // Glide (compiler only if you use @GlideModule)
    implementation("com.github.bumptech.glide:glide:4.15.1")
    // kapt("com.github.bumptech.glide:compiler:4.15.1")

    // Glide
    kapt("com.github.bumptech.glide:compiler:4.16.0")

    // Fragment KTX
    //implementation("androidx.fragment:fragment-ktx:1.6.2")

    implementation("com.google.firebase:firebase-analytics")

    // Firebase (BoM keeps versions aligned)
    // Firebase BoM – keeps Firebase versions in sync
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))

    // Firebase Auth
    implementation("com.google.firebase:firebase-auth-ktx")

    // Google Sign-In
    implementation("com.google.android.gms:play-services-auth:20.7.0")

    // Biometric
    implementation("androidx.biometric:biometric:1.1.0")


    implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
    implementation("androidx.navigation:navigation-ui-ktx:2.9.3")

}