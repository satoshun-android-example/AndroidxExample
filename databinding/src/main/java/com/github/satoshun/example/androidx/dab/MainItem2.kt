package com.github.satoshun.example.androidx.dab

import com.github.satoshun.example.androidx.dab.databinding.MainItemBinding
import com.xwray.groupie.databinding.BindableItem

class MainItem2 : BindableItem<MainItemBinding>() {
  override fun getLayout(): Int = R.layout.main_item

  override fun bind(binding: MainItemBinding, position: Int) {
  }
}
