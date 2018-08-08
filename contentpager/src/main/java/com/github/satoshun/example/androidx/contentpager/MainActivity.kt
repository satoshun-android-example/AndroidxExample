package com.github.satoshun.example.androidx.contentpager

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
      if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
      }

      requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
      return
    }
    runCursor()
  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    runCursor()
  }

  private fun runCursor() {
    val projection = arrayOf(
        MediaStore.Images.Thumbnails._ID,
        MediaStore.Images.Thumbnails.IMAGE_ID,
        MediaStore.Images.Thumbnails.DATA
    )
    val uri = MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI
    val cursor = contentResolver.query(
        uri,
        projection,
        null,
        null,
        MediaStore.Images.Thumbnails.IMAGE_ID
    )
    val da = mutableListOf<Data>()
    cursor?.moveToFirst()
    while (cursor?.moveToNext() == true) {
      val id = cursor.getLong(cursor.getColumnIndex(MediaStore.Images.Thumbnails.IMAGE_ID))

      val bitmap = MediaStore.Images.Thumbnails.getThumbnail(
          contentResolver,
          id,
          MediaStore.Images.Thumbnails.MINI_KIND,
          null
      )
      da += Data(bitmap)
    }
    recycler.adapter = MainAdapter(da)
    cursor?.close()
  }
}

private class Data(val bitmap: Bitmap)

private class MainAdapter(
  private val d: List<Data>
) : RecyclerView.Adapter<MainViewHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
    return MainViewHolder(ImageView(parent.context))
  }

  override fun getItemCount(): Int = d.size

  override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
    holder.imageView.setImageBitmap(d[position].bitmap)
  }
}

private class MainViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)
