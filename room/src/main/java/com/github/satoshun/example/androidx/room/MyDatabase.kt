package com.github.satoshun.example.androidx.room

import androidx.paging.DataSource
import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Fts3
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.RoomWarnings

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
@Fts3
data class Author(
  @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "rowid") var id: Long? = null,
  val name: String
)

@Dao
interface AuthorDao {
  @Insert
  fun insert(author: Author): Long

  @SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
  @Query("select * FROM authors")
  fun getAuthors(): DataSource.Factory<Int, Author>

  @Query("SELECT * FROM authors WHERE name MATCH :word")
  fun getAuthors(word: String): DataSource.Factory<Int, Author>
}
