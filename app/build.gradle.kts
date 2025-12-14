plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.newfeed.app"
    compileSdk = 36

    defaultConfig {
        // NOTE: `applicationId` must be unique on Google Play. Keep `namespace` as-is to avoid refactoring packages.
        applicationId = "com.newfeed.app.new"
        minSdk = 24
        targetSdk = 36
        versionCode = 5
        versionName = "1.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            // Enables code-related app optimization.
            isMinifyEnabled = true

            // Enables resource shrinking.
            isShrinkResources = true

            // Generate native debug symbols for Play Console (crash/ANR symbolication).
            // - "SYMBOL_TABLE": smaller upload, good default
            // - "FULL": best symbolication, larger
            ndk {
                debugSymbolLevel = "SYMBOL_TABLE"
            }

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
        jniLibs {
            // Prevent Gradle from stripping native libraries so symbol tables can be extracted/uploaded.
            // If you want to limit this, replace with e.g. "**/libandroidx.graphics.path.so"
            keepDebugSymbols += setOf("**/*.so")
        }
    }
}

dependencies {
    // Core Android
    implementation("androidx.core:core-ktx:1.17.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.4")
    implementation("androidx.activity:activity-compose:1.11.0")

    // Compose
    implementation(platform("androidx.compose:compose-bom:2025.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")
    
    // Navigation
    implementation("androidx.navigation:navigation-compose:2.9.5")
    
    // Hilt for Dependency Injection
    implementation("com.google.dagger:hilt-android:2.54")
    ksp("com.google.dagger:hilt-android-compiler:2.54")
    implementation("androidx.hilt:hilt-navigation-compose:1.3.0")
    
    // Room Database
    implementation("androidx.room:room-runtime:2.8.2")
    implementation("androidx.room:room-ktx:2.8.2")
    ksp("androidx.room:room-compiler:2.8.2")
    
    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
    
    // Coil for Image Loading
    implementation("io.coil-kt:coil-compose:2.4.0")
    
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.4")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.9.4")
    
    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
    androidTestImplementation(platform("androidx.compose:compose-bom:2025.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}

// Creates a ZIP you can upload to Google Play Console as "Native debug symbols".
// Note: If a dependency ships stripped .so files, symbolication may still be limited,
// but uploading this ZIP will satisfy Play's "missing debug symbols" warning and can help in some cases.
tasks.register<Zip>("exportPlayNativeDebugSymbols") {
    dependsOn("bundleRelease")

    val aab = layout.buildDirectory.file("outputs/bundle/release/app-release.aab")

    from(zipTree(aab)) {
        include("base/lib/**")
        includeEmptyDirs = false

        // Convert paths from "base/lib/..." to "lib/..." as expected by Play.
        eachFile {
            val segs = relativePath.segments
            if (segs.isNotEmpty() && segs[0] == "base") {
                relativePath = RelativePath(true, *segs.drop(1).toTypedArray())
            }
        }
    }

    archiveFileName.set("native-debug-symbols.zip")
    destinationDirectory.set(rootProject.layout.projectDirectory.dir("play-console"))
}
