object Vers {
  val compile_sdk = 28
  val min_sdk = 25
  val target_sdk = 28

  val kotlin = "1.3.10"
  val couroutine = "1.0.0"

  val navigation = "1.0.0-alpha07"
}

object Libs {
  val android_plugin = "com.android.tools.build:gradle:3.2.1"
  val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Vers.kotlin}"
  val safeargs_plugin = "android.arch.navigation:navigation-safe-args-gradle-plugin:${Vers.navigation}"
  val versions_plugin = "com.github.ben-manes:gradle-versions-plugin:0.20.0"

  val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Vers.kotlin}"
  val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Vers.couroutine}"
  val ui_coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Vers.couroutine}"

  val ktx = "androidx.core:core-ktx:1.0.1"
  val activityx = "androidx.activity:activity-ktx:1.0.0-alpha01"
  val fragmentx = "androidx.fragment:fragment-ktx:1.1.0-alpha01"

  val appcompat = "androidx.appcompat:appcompat:1.0.2"
  val recyclerview = "androidx.recyclerview:recyclerview:1.0.0"
  val constraintlayout = "androidx.constraintlayout:constraintlayout:2.0.0-alpha2"
  val cardview = "androidx.cardview:cardview:1.0.0"

  val contentpager = "androidx.contentpager:contentpager:1.0.0-rc01"
  val webkit = "androidx.webkit:webkit:1.0.0"
  val media2 = "androidx.media2:media2:1.0.0-alpha03"

  val material = "com.google.android.material:material:1.1.0-alpha01"
  val paging = "androidx.paging:paging-runtime:2.1.0-beta01"
  val browser = "androidx.browser:browser:1.0.0"
  val navigation = "android.arch.navigation:navigation-fragment-ktx:${Vers.navigation}"
  val navigation_ui = "android.arch.navigation:navigation-ui-ktx:${Vers.navigation}"

  val room_common = "androidx.room:room-common:2.1.0-alpha02"
  val room_runtime = "androidx.room:room-runtime:2.1.0-alpha02"
  val room_compiler = "androidx.room:room-compiler:2.1.0-alpha02"

  val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.0.0"
  val livedata = "androidx.lifecycle:lifecycle-livedata:2.0.0"
  val lifecycle_compiler = "androidx.lifecycle:lifecycle-compiler:2.0.0"

  val biometric = "androidx.biometric:biometric:1.0.0-alpha02"

  val emoji = "androidx.emoji:emoji:1.0.0"
  val emoji_compat = "androidx.emoji:emoji-appcompat:1.0.0"
  val emoji_bundled = "androidx.emoji:emoji-bundled:1.0.0"

  val multidex = "androidx.multidex:multidex:2.0.0"

  val rxwebview = "com.github.satoshun.RxWebView:rxwebview-kotlin:2.3.0"

  val rxjava = "io.reactivex.rxjava2:rxjava:2.2.0"
  val rxandroid = "io.reactivex.rxjava2:rxandroid:2.1.0"

  val groupie = "com.xwray:groupie:2.1.0"
  val groupie_databinding = "com.xwray:groupie-databinding:2.1.0"

  val android_annotation = "androidx.annotation:annotation:1.0.0"

  val junit = "junit:junit:4.12"
  val truth = "com.google.truth:truth:0.42"
  val mockito_kotlin = "com.nhaarman:mockito-kotlin-kt1.1:1.5.0"
  val test_runner = "androidx.test:runner:1.1.0"
  val test_rule = "androidx.test:rules:1.1.0"
  val espresso = "androidx.test.espresso:espresso-core:3.1.0"
}
