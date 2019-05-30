package com.github.satoshun.example.androidx.emoji

import android.app.Application
import androidx.core.provider.FontRequest
import androidx.emoji.text.EmojiCompat
import androidx.emoji.text.FontRequestEmojiCompatConfig

class App : Application() {
  override fun onCreate() {
    super.onCreate()

    val fontRequest = FontRequest(
      "com.google.android.gms.fonts",
      "com.google.android.gms",
      "Noto Color Emoji Compat",
      R.array.com_google_android_gms_fonts_certs
    )
    val config = FontRequestEmojiCompatConfig(this, fontRequest)
      .setReplaceAll(true)
    EmojiCompat.init(config)
  }
}
