plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlinx-serialization")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.movies"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.movies"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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
//    packaging {
//        resources.excludes.add("META-INF/*")
//        resources.excludes.add("mozilla/*")
//        resources.excludes.add("kotlin/coroutines/coroutines.kotlin_builtins")
//        resources.excludes.add("kotlin/kotlin.kotlin_builtins")
//        resources.excludes.add("kotlin/reflect/reflect.kotlin_builtins")
//        resources.excludes.add("kotlin/collections/collections.kotlin_builtins")
//        resources.excludes.add("kotlin/annotation/annotation.kotlin_builtins")
//        resources.excludes.add("kotlin/internal/*")
//        resources.excludes.add("kotlin/ranges/*")
//        resources.excludes.add("xsd/*")
//    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")
//    implementation("androidx.databinding:databinding-runtime:8.1.1")
//    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:2.6.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")
    implementation("io.coil-kt:coil:2.4.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}