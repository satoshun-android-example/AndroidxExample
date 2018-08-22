package com.github.satoshun.example.androidx.contentpager

import androidx.contentpager.content.ContentPager
import androidx.contentpager.content.Query
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class CoroutineQueryRunner : ContentPager.QueryRunner {
  private val mgr = mutableMapOf<Int, Job>()

  override fun query(query: Query, callback: ContentPager.QueryRunner.Callback) {
    mgr[query.id] = launch(UI) {
      callback.onQueryFinished(query, query.load(callback).await())
    }
  }

  private fun Query.load(callback: ContentPager.QueryRunner.Callback) =
      async { callback.runQueryInBackground(this@load)!! }

  override fun isRunning(query: Query): Boolean {
    return mgr[query.id]?.isActive == true
  }

  override fun cancel(query: Query) {
    mgr[query.id]?.cancel()
  }
}
