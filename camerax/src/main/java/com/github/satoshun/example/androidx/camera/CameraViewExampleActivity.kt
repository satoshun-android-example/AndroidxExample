package com.github.satoshun.example.androidx.camera

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Rect
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.FlashMode
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageProxy
import androidx.camera.core.VideoCapture
import androidx.camera.view.CameraView
import androidx.core.content.ContextCompat
import androidx.core.view.postDelayed
import kotlinx.android.synthetic.main.camera_view_act.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

private val REQUIRED_PERMISSIONS =
  arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE)

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

    scaleType.setOnClickListener {
      cameraView.scaleType = if (cameraView.scaleType == CameraView.ScaleType.CENTER_CROP) {
        CameraView.ScaleType.CENTER_INSIDE
      } else {
        CameraView.ScaleType.CENTER_CROP
      }
      scaleType.text = cameraView.scaleType.toString()
    }
    scaleType.text = cameraView.scaleType.toString()

    captureMode.setOnClickListener {
      cameraView.captureMode = when {
        cameraView.captureMode == CameraView.CaptureMode.IMAGE -> CameraView.CaptureMode.VIDEO
        cameraView.captureMode == CameraView.CaptureMode.VIDEO -> CameraView.CaptureMode.MIXED
        else -> CameraView.CaptureMode.IMAGE
      }
      captureMode.text = cameraView.captureMode.toString()
    }
    captureMode.text = cameraView.captureMode.toString()

    facing.setOnClickListener {
      cameraView.toggleCamera()
//      cameraView.setCameraByLensFacing(CameraX.LensFacing.FRONT)
//      cameraView.setCameraByLensFacing(CameraX.LensFacing.BACK)

      facing.text = cameraView.cameraLensFacing.toString()
    }
    facing.text = cameraView.cameraLensFacing.toString()

    focus.setOnClickListener {
      cameraView.focus(
        Rect(0, 0, 100, 100),
        Rect(0, 0, 100, 100)
      )
    }

    flash.setOnClickListener {
      cameraView.flash = if (cameraView.flash == FlashMode.OFF) {
        FlashMode.ON
      } else {
        FlashMode.OFF
      }
      flash.text = cameraView.flash.toString()
    }
    flash.text = cameraView.flash.toString()

    zoom.setOnClickListener {
      cameraView.zoomLevel = cameraView.zoomLevel + 3f
      zoom.text = cameraView.zoomLevel.toString()
    }
    zoom.text = cameraView.zoomLevel.toString()

//    pinch

    torch.setOnClickListener {
      cameraView.enableTorch(!cameraView.isTorchOn)
    }

    record.setOnClickListener {
      cameraView.startRecording(createNewFile(), object : VideoCapture.OnVideoSavedListener {
        override fun onVideoSaved(file: File) {
          Log.d("success", "$file")
        }

        override fun onError(useCaseError: VideoCapture.UseCaseError?, message: String?, cause: Throwable?) {
          Log.d("success", "$useCaseError $message $cause")
        }
      })

      record.postDelayed(5000) {
        cameraView.stopRecording()
      }
    }
  }
}

private fun createNewFile(): File =
  File(
    Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM),
    SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS", Locale.US).format(System.currentTimeMillis()) + ".mp4"
  )
