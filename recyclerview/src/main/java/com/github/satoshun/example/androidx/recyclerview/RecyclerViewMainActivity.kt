package com.github.satoshun.example.androidx.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.main_act.*
import kotlinx.android.synthetic.main.main_item.view.*

class RecyclerViewMainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_act)

    val adapter = MainAdapter((0..100).map { Data("test$it") })
    recycler.adapter = adapter
  }
}

class MainAdapter(
  private val data: List<Data>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
    return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.main_item, parent, false))
  }

  override fun getItemCount(): Int = data.size

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    val d =  data[position]
    holder.itemView.title.text = d.name
  }
}

class MainViewHolder(item: View) : RecyclerView.ViewHolder(item)

data class Data(
  val name: String
)
