package dependencies

object Application {
    const val applicationId = "com.enaz.cartrack.main.ui"
}

object Versions {
    const val compileSdkVersion = 29
    const val buildToolsVersion = "29.0.3"

    const val minSdkVersion = 23
    const val targetSdkVersion = 29

    const val versionCode = 1
    const val versionName = "1.0"

    const val kotlin_version = "1.3.72"
    const val gradle = "4.0.0"
    const val navigation = "2.3.0"
    const val core_ktx = "1.3.0"
    const val appcompat = "1.1.0"
    const val constraintlayout = "1.1.3"
    const val legacy_support = "1.0.0"
    const val lifecycle = "2.2.0"
    const val room = "2.2.3"

    //Google
    const val dagger = "2.25.3"
    const val materialDesign = "1.2.0-alpha03"
    const val play_services_maps = "17.0.0"

    //Retrofit
    const val retrofit = "2.6.0"
    const val loggingInterceptor = "3.12.0"

    //Test
    const val junit = "4.12"
    const val ext_junit = "1.1.1"
    const val espresso_core = "3.2.0"
}

object Libs {

    private object Gradle {
        const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin_version}"
    }

    private object Android {
        const val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin_version}"
        const val core_ktx = "androidx.core:core-ktx:${Versions.core_ktx}"
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}"

        const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacy_support}"

        //lifecycle
        const val lifecycleCommon = "androidx.lifecycle:lifecycle-common:${Versions.lifecycle}"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime:${Versions.lifecycle}"
        const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
        const val lifecycleLivedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val lifecycle_viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"

        // Navigation
        const val navigationPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
        const val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
        const val navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"

        const val materialDesign = "com.google.android.material:material:${Versions.materialDesign}"
    }

    private object Google {
        const val core = "com.google.dagger:dagger:${Versions.dagger}"
        const val android = "com.google.dagger:dagger-android:${Versions.dagger}"
        const val androidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
        const val processor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
        const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
        const val playServicesMaps = "com.google.android.gms:play-services-maps:${Versions.play_services_maps}"
    }

    private object Database {
        const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
        const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
        const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
        const val roomRxJava = "androidx.room:room-rxjava2:${Versions.room}"
        const val roomGuava = "androidx.room:room-guava:${Versions.room}"
        const val roomTesting = "androidx.room:room-testing:${Versions.room}"
    }

    private object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofitGSONConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    }

    private object Test {
        const val junit = "junit:junit:${Versions.junit}"
        const val ext_junit = "androidx.test.ext:junit:${Versions.ext_junit}"
        const val espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso_core}"
    }
}
