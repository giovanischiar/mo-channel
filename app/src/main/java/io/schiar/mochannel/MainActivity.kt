package io.schiar.mochannel

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

class MainActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val videoUriString = "http://192.168.0.12:8080/Pica Pau/Pica-Pau.WEB.DUB-WWW.BLUDV.COM (1).mkv"
        val videoUri = Uri.parse(videoUriString)
        val player = ExoPlayer.Builder(applicationContext).build()
        val playerView = findViewById<PlayerView>(R.id.video)
        playerView.player = player
        val mediaItem = MediaItem.fromUri(videoUri)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }
}