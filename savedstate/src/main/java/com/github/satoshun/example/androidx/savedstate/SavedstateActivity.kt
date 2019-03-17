package com.github.satoshun.example.androidx.savedstate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateVMFactory
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.saved_activity_main.*

class SavedstateActivity : AppCompatActivity() {
  private val viewModel by lazy {
    ViewModelProviders
      .of(
        this,
        SavedStateVMFactory(this)
      )
      .get(ExampleViewModel::class.java)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.saved_activity_main)

//    val registry = bundleSavedStateRegistry
//    registry.registerSavedStateProvider("state") {
//      bundleOf("hoge" to "fuga")
//    }
//
//    val data = registry.consumeRestoredStateForKey("state")
//    println(data)

    viewModel.value.observe(this, Observer {
      name.text = it!!
    })

    var i = 0
    update.setOnClickListener {
      viewModel.updateUserName("${i++} san")
    }
  }
}
