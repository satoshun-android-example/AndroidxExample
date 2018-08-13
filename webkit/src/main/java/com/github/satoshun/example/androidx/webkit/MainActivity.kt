package com.github.satoshun.example.androidx.webkit

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.webkit.WebResourceErrorCompat
import androidx.webkit.WebViewClientCompat
import androidx.webkit.WebViewCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    webview.webViewClient = object : WebViewClientCompat() {
      override fun onReceivedHttpError(
        view: WebView,
        request: WebResourceRequest,
        errorResponse: WebResourceResponse
      ) {
        super.onReceivedHttpError(view, request, errorResponse)
        Toast
            .makeText(this@MainActivity, "onReceivedHttpError ${errorResponse.statusCode}", Toast.LENGTH_LONG)
            .show()
      }

      override fun onReceivedError(
        view: WebView,
        request: WebResourceRequest,
        error: WebResourceErrorCompat
      ) {
        super.onReceivedError(view, request, error)
        Toast
            .makeText(this@MainActivity, "onReceivedError ${error.description}", Toast.LENGTH_LONG)
            .show()
      }

      override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)

        WebViewCompat.postVisualStateCallback(webview, 0) {
          Toast
              .makeText(this@MainActivity, "postVisual $it", Toast.LENGTH_LONG)
              .show()
        }
      }
    }

    @SuppressLint("SetJavaScriptEnabled")
    webview.settings.javaScriptEnabled = true

    success.setOnClickListener { webview.loadUrl("https://www.google.com/") }
    notfound.setOnClickListener { webview.loadUrl("https://developer.android.com/jetpack/docs/release-notesgaga") }
    never_reach.setOnClickListener { webview.loadUrl("https://developergaga.android.com/jetpack/docs/release-notesgaga") }
  }
}
