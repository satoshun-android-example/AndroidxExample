package com.github.satoshun.example.androidx.media2

import android.os.Bundle
import android.view.SurfaceHolder
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.media2.MediaItem
import androidx.media2.MediaPlayer
import androidx.media2.UriMediaItem
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executors

private const val SAMPLE = "https://sample-videos.com/video123/mp4/720/big_buck_bunny_720p_1mb.mp4"

class MainActivity : AppCompatActivity() {
  private lateinit var player: MediaPlayer

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    player = MediaPlayer(this)
    player.registerPlayerCallback(
      Executors.newCachedThreadPool(),
      object : MediaPlayer.PlayerCallback() {
        override fun onInfo(mp: MediaPlayer, item: MediaItem, what: Int, extra: Int) {
          super.onInfo(mp, item, what, extra)
        }
      }
    )

    player.setMediaItem(UriMediaItem
      .Builder(this, SAMPLE.toUri())
      .build()
    )
    player
      .prepare()
      .addListener(
        Runnable {
          player.play()
        },
        Executors.newCachedThreadPool())

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
