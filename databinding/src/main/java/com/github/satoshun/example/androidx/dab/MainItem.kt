package com.github.satoshun.example.androidx.dab

import androidx.databinding.ViewDataBinding
import com.xwray.groupie.databinding.BindableItem

class MainItem : BindableItem<ViewDataBinding>() {
  override fun getLayout(): Int = R.layout.main_item

  override fun bind(binding: ViewDataBinding, position: Int) {
  }
}
