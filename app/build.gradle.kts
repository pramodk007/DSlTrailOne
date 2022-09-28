plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs")
    id("kotlin-parcelize")
    id("kotlinx-serialization")
}
android {
    namespace = "com.example.movieexp"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.example.movieexp"
        minSdk = 25
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
        }
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    packagingOptions {
        resources {
            excludes += listOf("META-INF/proguard/androidx-annotations.pro", "META-INF/DEPENDENCIES")
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = freeCompilerArgs + listOf("-opt-in=kotlin.RequiresOptIn")
    }
    configurations.all {
        resolutionStrategy {
            exclude(group = "org.jetbrains.kotlinx", module = "kotlinx-coroutines-debug")
        }
    }
    testOptions {
        unitTests.isIncludeAndroidResources = true
        unitTests.isReturnDefaultValues = true
    }
    sourceSets {
        // Adds exported schema location as test app assets.
        getByName("androidTest").assets.srcDir("$projectDir/schemas")
    }
}

dependencies {

    implementation(project(Modules.HELPER))
    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")

    // AndroidX
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // activity - by viewModels
    implementation("androidx.fragment:fragment-ktx:1.5.3")
    implementation ("androidx.activity:activity-ktx:1.6.0")

    implementation("androidx.preference:preference-ktx:1.2.0")

    implementation("androidx.vectordrawable:vectordrawable:1.1.0")

    // Navigation Components
    implementation("androidx.navigation:navigation-runtime-ktx:2.5.2")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.2")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-common-java8:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-process:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-service:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")

    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    implementation("androidx.work:work-runtime-ktx:2.7.1")

    implementation("androidx.recyclerview:recyclerview-selection:1.1.0")

    implementation("androidx.transition:transition-ktx:1.4.1")

    implementation("com.google.android.material:material:1.8.0-alpha01")

    //hilt
    implementation ("com.google.dagger:hilt-android:2.44")
    kapt ("com.google.dagger:hilt-compiler:2.44")
    kapt ("androidx.hilt:hilt-compiler:1.0.0")
    implementation ("androidx.hilt:hilt-navigation-fragment:1.0.0")

    // Room
    implementation("androidx.room:room-runtime:2.4.3")
    kapt("androidx.room:room-compiler:2.4.3")
    implementation("androidx.room:room-rxjava3:2.4.3")
    implementation("androidx.room:room-ktx:2.4.3")
    implementation("androidx.room:room-paging:2.4.3")
    testImplementation("androidx.room:room-testing:2.4.3")

    // OkHttp
    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.8")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.8")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // Gson
    implementation("com.google.code.gson:gson:2.9.1")

    //moshi
    implementation ("com.squareup.moshi:moshi:1.14.0")
    implementation ("com.squareup.moshi:moshi-kotlin:1.14.0")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.13.2")
    kapt ("com.github.bumptech.glide:compiler:4.13.2")

    // Timber
    implementation ("com.jakewharton.timber:timber:5.0.1")

    // Window
    implementation ("androidx.window:window:1.0.0")

    //Navigation Animation
    implementation ("com.google.accompanist:accompanist-navigation-animation:0.16.1")

    //Accompanist
    implementation ("com.google.accompanist:accompanist-pager:0.25.1")
    implementation ("com.google.accompanist:accompanist-pager-indicators:0.25.1")

    //swipe refresh
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    //checker
    debugImplementation ("com.github.chuckerteam.chucker:library:3.5.2")

    //logger
    implementation ("com.orhanobut:logger:2.2.0")

    //Initializer
    implementation ("androidx.startup:startup-runtime:1.1.1")

    // Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("android.arch.core:core-testing:1.1.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test:rules:1.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    testImplementation("org.mockito:mockito-core:4.3.1")
    testImplementation("org.mockito:mockito-inline:4.3.1")
    testImplementation("org.mockito:mockito-android:4.3.1")
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
}