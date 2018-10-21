package com.github.satoshun.example.androidx.room

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase

@Database(
    entities = [
      Author::class
    ],
    version = 1
)
abstract class MyDatabase : RoomDatabase() {
  abstract fun author(): AuthorDao
}

@Entity(tableName = "authors")
data class Author(
  @PrimaryKey(autoGenerate = true) var id: Long? = null,
  val name: String
)

@Dao
interface AuthorDao {
  @Insert
  fun insert(author: Author): Long

  @Query("select * FROM authors")
  fun getAuthors(): DataSource.Factory<Int, Author>
}
