package com.github.satoshun.example.androidx.emoji

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.provider.FontRequest
import androidx.emoji.text.EmojiCompat
import androidx.emoji.text.FontRequestEmojiCompatConfig
import kotlinx.android.synthetic.main.emoji_main_act.*

class EmojiMainActivity : AppCompatActivity() {
  @SuppressLint("SetTextI18n")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.emoji_main_act)

    emoji1.text = "\uD83E\uDD23"
    emoji2.text = "\uD83E\uDD23"
    emoji3.text = "\uD83E\uDD23"
  }
}
