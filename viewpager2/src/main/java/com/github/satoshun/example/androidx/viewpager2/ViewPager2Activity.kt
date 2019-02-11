package com.github.satoshun.example.androidx.viewpager2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

class ViewPager2Activity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.viewpager2_activity_main)

    val viewPager: ViewPager2 = findViewById(R.id.viewpager)
    viewPager.adapter = FragmentStateAdapterExample(supportFragmentManager)
  }
}

internal class FragmentExample : Fragment() {
  companion object {
    operator fun invoke(count: Int): Fragment {
      return FragmentExample().apply {
        arguments = bundleOf("count" to count)
      }
    }
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return inflater.inflate(R.layout.viewpager2_frag, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val title = view.findViewById<TextView>(R.id.title)
    title.text = requireArguments().getInt("count", 0).toString()
  }
}

internal class FragmentStateAdapterExample(
  manager: FragmentManager
) : FragmentStateAdapter(manager) {
  override fun getItem(position: Int): Fragment {
    return FragmentExample(position)
  }

  override fun getItemCount(): Int {
    return 1 // todo work at 1
  }
}
