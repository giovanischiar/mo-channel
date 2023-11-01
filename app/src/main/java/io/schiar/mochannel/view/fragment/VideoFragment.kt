package io.schiar.mochannel.view.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import io.schiar.mochannel.R
import io.schiar.mochannel.view.MainActivity
import io.schiar.mochannel.viewmodel.VideoViewModel
import kotlinx.coroutines.launch

class VideoFragment : Fragment() {
    private lateinit var viewModel: VideoViewModel
    private lateinit var player: ExoPlayer

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_video, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = (requireActivity() as MainActivity).videoViewModel
        viewModel.loadEpisode()
        lifecycleScope.launch {
            viewModel.source.collect { urls ->
                val view = view ?: return@collect
                val context = context ?: return@collect
                player = ExoPlayer.Builder(context).build()
                player.setMediaItems(urls.map {
                    url -> MediaItem.fromUri(Uri.parse(url))
                })
                val playerView = view.findViewById<PlayerView>(R.id.video)
                playerView.player = player
                player.prepare()
                player.play()
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        player.stop()
    }
}