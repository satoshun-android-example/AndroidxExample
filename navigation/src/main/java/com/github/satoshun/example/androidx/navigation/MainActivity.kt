package com.github.satoshun.example.androidx.navigation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val fragment = nav_host_fragment
    Log.d("fragment", fragment.toString())
//    val con = findNavController(R.id.nav_host_fragment)
//    Log.d("fragment", con.toString())
//
//    val view: View = ActivityCompat.requireViewById(this, R.id.nav_host_fragment)
//    Log.d("fragment", view.toString())
  }
}
