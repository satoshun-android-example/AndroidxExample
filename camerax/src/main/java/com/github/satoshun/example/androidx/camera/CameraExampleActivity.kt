package com.github.satoshun.example.androidx.camera

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.camera_main_act.*

class CameraExampleActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.camera_main_act)

    cameraView.setOnClickListener {
      startActivity(Intent(this, CameraViewExampleActivity::class.java))
    }
  }
}
