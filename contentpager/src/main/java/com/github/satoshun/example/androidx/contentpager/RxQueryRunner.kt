package com.github.satoshun.example.androidx.contentpager

import androidx.contentpager.content.ContentPager
import androidx.contentpager.content.Query
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RxQueryRunner : ContentPager.QueryRunner {
  private val mgr = mutableMapOf<Int, Disposable>()

  override fun query(query: Query, callback: ContentPager.QueryRunner.Callback) {
    mgr[query.id] = Single
        .fromCallable {
          callback.runQueryInBackground(query)
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            {
              callback.onQueryFinished(query, it)
            },
            {}
        )
  }

  override fun isRunning(query: Query): Boolean {
    return mgr[query.id]?.isDisposed == false
  }

  override fun cancel(query: Query) {
    mgr[query.id]?.dispose()
  }
}
