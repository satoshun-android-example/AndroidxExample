package com.github.satoshun.example.androidx.camera

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class CameraViewExampleActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.camera_view_act)

    if (ContextCompat.checkSelfPermission(
        this,
        android.Manifest.permission.CAMERA
      ) != PackageManager.PERMISSION_GRANTED
    ) {
      throw IllegalStateException("App has not been granted CAMERA permission")
    }
  }
}
