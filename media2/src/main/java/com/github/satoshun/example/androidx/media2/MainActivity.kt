package com.github.satoshun.example.androidx.media2

import android.os.Bundle
import android.view.SurfaceHolder
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.media2.DataSourceDesc2
import androidx.media2.MediaPlayer2
import androidx.media2.UriDataSourceDesc2
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors

private const val SAMPLE = "https://www.sample-videos.com/video/mp4/720/big_buck_bunny_720p_1mb.mp4"

class MainActivity : AppCompatActivity() {
  private lateinit var player: MediaPlayer2

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    player = MediaPlayer2.create(this)
    player.setEventCallback(
        Executors.newCachedThreadPool(),
        object : MediaPlayer2.EventCallback() {
          override fun onInfo(mp: MediaPlayer2, dsd: DataSourceDesc2, what: Int, extra: Int) {
            if (what == 100) player.play()
          }
        }
    )

    player.setDataSource(UriDataSourceDesc2
        .Builder(this, SAMPLE.toUri())
        .build()
    )
    player.prepare()

    // todo
    surface.holder.addCallback(object : SurfaceHolder.Callback {
      override fun surfaceChanged(p0: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {
      }

      override fun surfaceDestroyed(holder: SurfaceHolder?) {
      }

      override fun surfaceCreated(holder: SurfaceHolder) {
        player.setSurface(holder.surface)
      }
    })
  }
}
