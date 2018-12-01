package com.github.satoshun.example.androidx.dab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.github.satoshun.example.androidx.dab.databinding.DataBindingMainActBinding

class DataBindingMainActivity : AppCompatActivity() {
  private lateinit var binding: DataBindingMainActBinding
  private lateinit var adapter: MainAdapter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.data_binding_main_act)
    adapter = MainAdapter()

    binding.recycler.adapter = adapter
    adapter.add()
  }
}
