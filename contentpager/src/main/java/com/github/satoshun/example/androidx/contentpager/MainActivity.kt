package com.github.satoshun.example.androidx.contentpager

import android.Manifest
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.contentpager.content.ContentPager
import androidx.core.os.bundleOf
import androidx.core.view.postDelayed
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  private lateinit var adapter: MainAdapter

  private var offset = 0
  private val limit = 10

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    adapter = MainAdapter(mutableListOf())
    recycler.adapter = adapter

    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
      if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
      }

      requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
      return
    }
    useContentPager()
  }

  override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    useContentPager()
  }

  private fun useContentPager() {
    val projection = arrayOf(
        MediaStore.Images.Thumbnails._ID,
        MediaStore.Images.Thumbnails.IMAGE_ID,
        MediaStore.Images.Thumbnails.DATA
    )
    val uri = MediaStore.Images.Thumbnails.EXTERNAL_CONTENT_URI
    val pager = ContentPager(
        contentResolver,
        CoroutineQueryRunner()
//        RxQueryRunner()
//        LoaderQueryRunner(this, loaderManager)
    )
    pager.query(
        uri,
        projection,
        bundleOf(
            ContentPager.QUERY_ARG_OFFSET to offset,
            ContentPager.QUERY_ARG_LIMIT to limit
        ),
        null
    ) { _, cursor ->
      cursor ?: return@query
      val count = cursor.count
      if (count == 0) return@query

      Toast
          .makeText(this, "load new data: $count", Toast.LENGTH_LONG)
          .show()

      offset += count
      cursor.populate()
      cursor.close()
      adapter.notifyDataSetChanged()

      // finished loading data
      if (count != limit) return@query

      recycler.postDelayed(3000) {
        useContentPager()
      }
    }
  }

  private fun useRawContentResolver() {
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
    cursor?.populate()
    cursor?.close()
    adapter.notifyDataSetChanged()
  }

  private fun Cursor.populate() {
    moveToFirst()
    while (moveToNext()) {
      val id = getLong(getColumnIndex(MediaStore.Images.Thumbnails.IMAGE_ID))

      val bitmap = MediaStore.Images.Thumbnails.getThumbnail(
          contentResolver,
          id,
          MediaStore.Images.Thumbnails.MINI_KIND,
          null
      )
      adapter.d.add(Data(bitmap))
    }
  }
}

private class Data(val bitmap: Bitmap)

private class MainAdapter(
  val d: MutableList<Data>
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
