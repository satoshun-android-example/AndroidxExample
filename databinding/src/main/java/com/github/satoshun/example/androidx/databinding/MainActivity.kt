package com.github.satoshun.example.androidx.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.github.satoshun.example.androidx.databinding.databinding.MainActBinding
import com.github.satoshun.example.androidx.databinding.databinding.MainItemBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import com.xwray.groupie.databinding.BindableItem

class MainActivity : AppCompatActivity() {
  private lateinit var binding: MainActBinding
  private lateinit var adapter: MainAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.main_act)
    adapter = MainAdapter()

    binding.recycler.adapter = adapter
    adapter.add()
  }
}

class MainAdapter : GroupAdapter<ViewHolder>() {
  fun add() {
    add(MainItem())
    add(MainItem2())
  }
}

class MainItem : BindableItem<ViewDataBinding>() {
  override fun getLayout(): Int = R.layout.main_item

  override fun bind(binding: ViewDataBinding, position: Int) {
  }
}

class MainItem2 : BindableItem<MainItemBinding>() {
  override fun getLayout(): Int = R.layout.main_item

  override fun bind(binding: MainItemBinding, position: Int) {
  }
}
