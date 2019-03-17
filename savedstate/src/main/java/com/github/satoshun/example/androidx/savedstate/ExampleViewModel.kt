package com.github.satoshun.example.androidx.savedstate

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

internal class ExampleViewModel(
  savedStateHandle: SavedStateHandle
) : ViewModel() {
  val value = savedStateHandle.getLiveData<String>("username")

  fun updateUserName(name: String) {
    value.postValue(name)
  }
}
