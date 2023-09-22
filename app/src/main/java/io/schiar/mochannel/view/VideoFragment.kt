package io.schiar.mochannel.view

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import io.schiar.mochannel.R
import io.schiar.mochannel.viewmodel.VideoViewModel

class VideoFragment: Fragment() {
    companion object {
        fun newInstance() = VideoFragment()
    }

    private lateinit var viewModel: VideoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[VideoViewModel::class.java]
        val view = view ?: return
        val videoUri = Uri.parse(viewModel.source.value)
        val player = ExoPlayer.Builder(requireContext()).build()
        val playerView = view.findViewById<PlayerView>(R.id.video)
        playerView.player = player
        val mediaItem = MediaItem.fromUri(videoUri)
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()
    }
}