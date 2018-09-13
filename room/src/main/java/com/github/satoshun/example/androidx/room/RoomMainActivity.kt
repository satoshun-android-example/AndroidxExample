package com.github.satoshun.example.androidx.room

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.LivePagedListBuilder
import androidx.room.Room

class RoomMainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val database = Room
        .databaseBuilder(this, MyDatabase::class.java, "database")
        .build()

    val list = LivePagedListBuilder(database.author().getAuthors(), 10).build()
  }
}
