package com.github.satoshun.example.androidx.dab

import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

class MainAdapter : GroupAdapter<ViewHolder>() {
  fun add() {
    add(MainItem())
    add(MainItem2())
  }
}
