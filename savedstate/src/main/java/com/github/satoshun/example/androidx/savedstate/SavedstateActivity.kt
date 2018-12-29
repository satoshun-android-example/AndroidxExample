package com.github.satoshun.example.androidx.savedstate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf

class SavedstateActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.saved_activity_main)

    val registry = getBundleSavedStateRegistry()
    registry.registerSavedStateProvider("state") {
      bundleOf("hoge" to "fuga")
    }

    val data = registry.consumeRestoredStateForKey("state")
    println(data)
  }
}
