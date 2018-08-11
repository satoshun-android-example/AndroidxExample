package com.github.satoshun.example.androidx.constraintlayout

import android.graphics.Bitmap
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
  private lateinit var adapter: MainAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    adapter = MainAdapter(
        mutableListOf(),
        MainItemCallback()
    )
    recycler.adapter = adapter
  }
}

private data class Data(val bitmap: Bitmap)

private class MainAdapter(
  val d: MutableList<Data>,
  diffCallback: DiffUtil.ItemCallback<Data>
) : PagedListAdapter<Data, MainViewHolder>(diffCallback) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
    return MainViewHolder(ImageView(parent.context))
  }

  override fun getItemCount(): Int = d.size

  override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
    holder.imageView.setImageBitmap(d[position].bitmap)
  }
}

private class MainViewHolder(val imageView: ImageView) : RecyclerView.ViewHolder(imageView)

private class MainItemCallback : DiffUtil.ItemCallback<Data>() {
  override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
    return oldItem == newItem
  }

  override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
    return oldItem == newItem
  }
}
