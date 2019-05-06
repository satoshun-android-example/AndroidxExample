package com.github.satoshun.example.androidx.camera

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.camera_view_act.*

private val REQUIRED_PERMISSIONS =
  arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)

private const val REQUEST_CODE_PERMISSIONS = 1

class CameraViewExampleActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.camera_view_act)

    if (!allPermissionsGranted()) {
      requestPermissions(
        REQUIRED_PERMISSIONS,
        REQUEST_CODE_PERMISSIONS
      )
      return
    }

    startCamera()
  }

  private fun allPermissionsGranted(): Boolean {
    for (permission in REQUIRED_PERMISSIONS) {
      if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
        return false
      }
    }
    return true
  }

  override fun onRequestPermissionsResult(
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
  ) {
    if (requestCode == REQUEST_CODE_PERMISSIONS) {
      if (allPermissionsGranted()) {
        startCamera()
      }
    }
  }

  private fun startCamera() {
    cameraView.bindToLifecycle(this)

    capture.setOnClickListener {
      cameraView.takePicture(object : ImageCapture.OnImageCapturedListener() {
        override fun onCaptureSuccess(image: ImageProxy, rotationDegrees: Int) {
          Log.d("success", "$image, $rotationDegrees")
        }

        override fun onError(useCaseError: ImageCapture.UseCaseError?, message: String?, cause: Throwable?) {
          Log.e("onError", "$useCaseError, $message, $cause")
        }
      })
    }
  }
}
