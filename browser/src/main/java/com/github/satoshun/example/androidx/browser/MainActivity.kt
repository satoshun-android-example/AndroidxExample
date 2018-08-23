package com.github.satoshun.example.androidx.browser

import android.app.PendingIntent
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.browseractions.BrowserActionItem
import androidx.browser.browseractions.BrowserActionsIntent
import androidx.browser.customtabs.CustomTabsCallback
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.core.net.toUri
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    browseractions()
    customtabs()
  }

  private fun browseractions() {
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
              BrowserActionItem("HOGEHOGE", pending, android.R.drawable.sym_def_app_icon)
          ),
          null
      )
    }
  }

  private fun customtabs() {
    customtab.setOnClickListener {
      val tabIntent = CustomTabsIntent.Builder()
          .setShowTitle(true)
          .build()
      tabIntent.launchUrl(this, "http://google.com".toUri())

//      ContextCompat.startActivity(
//          this,
//          Intent(Intent.ACTION_VIEW).apply {
//            val bundle = Bundle()
//            BundleCompat.putBinder(
//                bundle,
//                "android.support.customtabs.extra.SESSION",
//                null
//            )
//            putExtras(bundle)
//
//            putExtra(CustomTabsIntent.EXTRA_ENABLE_INSTANT_APPS, true)
//            data = "http://google.com".toUri()
//          },
//          null
//      )
    }

    customtab_session.setOnClickListener {
      CustomTabsClient.bindCustomTabsService(
          this,
          CustomTabsClient.getPackageName(this@MainActivity, null),
          object : CustomTabsServiceConnection() {
            override fun onCustomTabsServiceConnected(name: ComponentName, client: CustomTabsClient) {
              val conn = this
              val session = client.newSession(object : CustomTabsCallback() {
                override fun onNavigationEvent(navigationEvent: Int, extras: Bundle?) {
                  Log.d("onNavigationEvent", navigationEvent.toString())
                  if (navigationEvent == CustomTabsCallback.TAB_HIDDEN) {
                    this@MainActivity.unbindService(conn)
                  }
                }

                override fun extraCallback(callbackName: String, args: Bundle?) {
                  Log.d("extraCallback", callbackName)
                }
              })

              val tabIntent = CustomTabsIntent.Builder(session)
                  .setShowTitle(true)
                  .build()
              tabIntent.launchUrl(
                  this@MainActivity,
                  "http://google.com".toUri()
              )

//              customtab_session.postDelayed(5000) {
//                session.mayLaunchUrl("https://github.com".toUri(), Bundle(), emptyList())
//              }
            }

            override fun onServiceDisconnected(name: ComponentName) {
              Log.d("ServiceDisconnected", name.toString())
            }
          })
    }
  }
}
