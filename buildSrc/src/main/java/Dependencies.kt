object Vers {
  const val compile_sdk = 28
  const val min_sdk = 25
  const val target_sdk = 28

  const val kotlin = "1.3.10"
  const val couroutine = "1.0.1"

  const val navigation = "1.0.0-alpha07"
}

object Libs {
  const val android_plugin = "com.android.tools.build:gradle:3.4.0-alpha06"
  const val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Vers.kotlin}"
  const val safeargs_plugin = "android.arch.navigation:navigation-safe-args-gradle-plugin:${Vers.navigation}"
  const val versions_plugin = "com.github.ben-manes:gradle-versions-plugin:0.20.0"

  const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Vers.kotlin}"
  const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Vers.couroutine}"
  const val ui_coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Vers.couroutine}"

  const val ktx = "androidx.core:core-ktx:1.0.1"
  const val activityx = "androidx.activity:activity-ktx:1.0.0-alpha01"
  const val fragmentx = "androidx.fragment:fragment-ktx:1.1.0-alpha01"

  const val appcompat = "androidx.appcompat:appcompat:1.0.2"
  const val recyclerview = "androidx.recyclerview:recyclerview:1.0.0"
  const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.0-alpha2"
  const val cardview = "androidx.cardview:cardview:1.0.0"

  const val contentpager = "androidx.contentpager:contentpager:1.0.0-rc01"
  const val webkit = "androidx.webkit:webkit:1.0.0"
  const val media2 = "androidx.media2:media2:1.0.0-alpha03"

  const val material = "com.google.android.material:material:1.1.0-alpha01"
  const val paging = "androidx.paging:paging-runtime:2.1.0-beta01"
  const val browser = "androidx.browser:browser:1.0.0"
  const val navigation = "android.arch.navigation:navigation-fragment-ktx:${Vers.navigation}"
  const val navigation_ui = "android.arch.navigation:navigation-ui-ktx:${Vers.navigation}"

  const val room_common = "androidx.room:room-common:2.1.0-alpha02"
  const val room_runtime = "androidx.room:room-runtime:2.1.0-alpha02"
  const val room_compiler = "androidx.room:room-compiler:2.1.0-alpha02"

  const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.0.0"
  const val livedata = "androidx.lifecycle:lifecycle-livedata:2.0.0"
  const val lifecycle_compiler = "androidx.lifecycle:lifecycle-compiler:2.0.0"

  const val biometric = "androidx.biometric:biometric:1.0.0-alpha02"

  const val emoji = "androidx.emoji:emoji:1.0.0"
  const val emoji_compat = "androidx.emoji:emoji-appcompat:1.0.0"
  const val emoji_bundled = "androidx.emoji:emoji-bundled:1.0.0"

  const val multidex = "androidx.multidex:multidex:2.0.0"

  const val rxwebview = "com.github.satoshun.RxWebView:rxwebview-kotlin:2.3.0"

  const val rxjava = "io.reactivex.rxjava2:rxjava:2.2.0"
  const val rxandroid = "io.reactivex.rxjava2:rxandroid:2.1.0"

  const val groupie = "com.xwray:groupie:2.3.0"
  const val groupie_databinding = "com.xwray:groupie-databinding:2.3.0"

  const val android_annotation = "androidx.annotation:annotation:1.0.0"

  const val junit = "junit:junit:4.12"
  const val truth = "com.google.truth:truth:0.42"
  const val mockito_kotlin = "com.nhaarman:mockito-kotlin-kt1.1:1.5.0"
  const val test_runner = "androidx.test:runner:1.1.0"
  const val test_rule = "androidx.test:rules:1.1.0"
  const val espresso = "androidx.test.espresso:espresso-core:3.1.0"
}
