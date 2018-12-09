package com.github.satoshun.example.androidx.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.github.satoshun.example.common.BaseActivity
import kotlinx.android.synthetic.main.main_act.*
import kotlinx.android.synthetic.main.main_item.view.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RecyclerViewMainActivity : BaseActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_act)

//    basicAdapter()
    diffAdapter()
  }

  private fun basicAdapter() {
    val adapter = MainAdapter((0..100).map { Data("test$it") }.toMutableList())
    recycler.adapter = adapter

    launch {
      while (true) {
        delay(2000)
        adapter.data.shuffle()
        adapter.notifyDataSetChanged()
      }
    }
  }

  private fun diffAdapter() {
    val adapter = MainListAdapter()
    recycler.adapter = adapter
    adapter.submitList((0..100).map { Data("test$it") })

    launch {
      while (true) {
        delay(2000)
        adapter.submitList((0..100).map { Data("test$it") }.shuffled())
      }
    }
  }
}

class MainListAdapter : ListAdapter<Data, RecyclerView.ViewHolder>(dataCallback) {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false))
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val d = getItem(position)
    holder.itemView.title.text = d.name
  }
}

private val dataCallback = object : DiffUtil.ItemCallback<Data>() {
  override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
    return oldItem == newItem
  }

  override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
    return oldItem == newItem
  }
}

class MainAdapter(
  val data: MutableList<Data>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false))
  }

  override fun getItemCount(): Int = data.size

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val d = data[position]
    holder.itemView.title.text = d.name
  }
}

class MainViewHolder(item: View) : RecyclerView.ViewHolder(item)

data class Data(
  val name: String
)
