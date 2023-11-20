plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.fyrl29074.data"
    compileSdk = 33

    defaultConfig {
        minSdk = 24

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
    implementation(project(":domain"))

    implementation("androidx.core:core-ktx:1.12.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")

    // Retrofit
    val retrofitVersion = "2.9.0"
    val okhttpVersion = "4.12.0"
    implementation("com.squareup.retrofit2:retrofit:${retrofitVersion}")
    implementation("com.squareup.retrofit2:converter-gson:${retrofitVersion}")
    implementation("com.squareup.okhttp3:logging-interceptor:${okhttpVersion}")
    implementation("com.squareup.okhttp3:okhttp:${okhttpVersion}")

    // Kotlin coroutines
    val coroutinesVersion = "1.7.3"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    // Koin for Android
    val koinVersion = "3.4.0"
    implementation("io.insert-koin:koin-android:$koinVersion")
}