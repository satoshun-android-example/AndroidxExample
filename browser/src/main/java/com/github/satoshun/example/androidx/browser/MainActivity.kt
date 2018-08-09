package com.github.satoshun.example.androidx.browser

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.browseractions.BrowserActionItem
import androidx.browser.browseractions.BrowserActionsIntent
import androidx.core.net.toUri
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    open_link.setOnClickListener {
      //      val browser = BrowserActionsIntent
      //          .Builder(this, "https://twitter.com".toUri())
      //          .build()
      //      startActivity(browser.intent)

      BrowserActionsIntent.openBrowserAction(
          this,
          "https://twitter.com".toUri()
      )
    }

    open_link_menu_items.setOnClickListener {
      val pending = PendingIntent.getActivity(
          this,
          1,
          Intent(Intent.ACTION_VIEW),
          PendingIntent.FLAG_ONE_SHOT
      )
      BrowserActionsIntent.openBrowserAction(
          this,
          "https://twitter.com".toUri(),
          BrowserActionsIntent.URL_TYPE_IMAGE,
          arrayListOf(
              BrowserActionItem("HOGEHOGE", pending, R.drawable.abc_text_cursor_material)
          ),
          null
      )
    }
  }
}
