package com.github.satoshun.example.androidx.paging

import android.graphics.Bitmap
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedListAdapter
import androidx.paging.PositionalDataSource
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class PagingActivity : AppCompatActivity() {
  private lateinit var adapter: MainAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    adapter = MainAdapter(MainItemCallback())
    recycler.adapter = adapter

    val factory = object : DataSource.Factory<Int, Data>() {
      override fun create(): DataSource<Int, Data> {
        return MyDataSource()
      }
    }
    val paged = LivePagedListBuilder(
        factory,
        10
    )
    paged.build().observe(this, Observer {
      adapter.submitList(it!!)
    })
  }
}

class MyDataSource : PositionalDataSource<Data>() {
  override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Data>) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Data>) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}

data class Data(val bitmap: Bitmap)

private class MainAdapter(
  diffCallback: DiffUtil.ItemCallback<Data>
) : PagedListAdapter<Data, MainViewHolder>(diffCallback) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
    return MainViewHolder(ImageView(parent.context))
  }

  override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
    val item = getItem(position)!!
    holder.imageView.setImageBitmap(item.bitmap)
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
