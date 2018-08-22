package com.github.satoshun.example.androidx.contentpager

import android.database.Cursor
import androidx.contentpager.content.ContentPager
import androidx.contentpager.content.Query
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import kotlin.coroutines.experimental.suspendCoroutine

class CoroutineQueryRunner : ContentPager.QueryRunner {
  private val mgr = mutableMapOf<Int, Job>()

  override fun query(query: Query, callback: ContentPager.QueryRunner.Callback) {
    mgr[query.id] = launch(UI) {
      callback.onQueryFinished(query, query.load(callback))
    }
  }

  private suspend fun Query.load(callback: ContentPager.QueryRunner.Callback) =
      suspendCoroutine<Cursor> {
        it.resume(callback.runQueryInBackground(this)!!)
      }

  override fun isRunning(query: Query): Boolean {
    return mgr[query.id]?.isActive == true
  }

  override fun cancel(query: Query) {
    mgr[query.id]?.cancel()
  }
}
