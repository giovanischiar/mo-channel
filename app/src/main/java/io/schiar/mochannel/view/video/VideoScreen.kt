package io.schiar.mochannel.view.video

import android.net.Uri
import android.view.KeyEvent
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaItem.SubtitleConfiguration
import androidx.media3.common.MimeTypes
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import com.google.common.collect.ImmutableList
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.launch

@OptIn(UnstableApi::class) @Composable
fun VideoScreen(currentTVShowEpisodeURLsUiState: CurrentTVShowEpisodeURLsUiState) {
    val context = LocalContext.current
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val coroutineScope = rememberCoroutineScope()
    val exoPlayer = remember { ExoPlayer.Builder(context).build().apply { prepare() } }

    val urls = when (currentTVShowEpisodeURLsUiState) {
        is CurrentTVShowEpisodeURLsUiState.Loading -> emptyList()
        is CurrentTVShowEpisodeURLsUiState.CurrentEpisodesFromSeasonLoaded -> {
            currentTVShowEpisodeURLsUiState.urls
        }
    }
    var backHandlerEnabled by remember { mutableStateOf(value = true) }

    LaunchedEffect(urls) {
        exoPlayer.setMediaItems(urls.map {
            url ->
                val subtitles = SubtitleConfiguration
                    .Builder(Uri.parse(url.dropLast(n = 3) + "srt"))
                    .setMimeType(MimeTypes.APPLICATION_SUBRIP)
                    .setLanguage("en")
                    .setSelectionFlags(C.SELECTION_FLAG_DEFAULT)
                    .build()
                MediaItem.Builder()
                    .setUri(url)
                    .setSubtitleConfigurations(ImmutableList.of(subtitles))
                    .build()
        })
        exoPlayer.playWhenReady = true
    }

    val playerView = remember {
        PlayerView(context).apply {
            fun onIsPlayerChanged(isPlaying: Boolean) { keepScreenOn = isPlaying }
            player = exoPlayer.apply {
                addListener(object : Player.Listener {
                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        onIsPlayerChanged(isPlaying = isPlaying)
                    }
                })
            }
            layoutParams =
                FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
        }
    }

    BackHandler(enabled = backHandlerEnabled) {
        if (playerView.isControllerFullyVisible) {
            playerView.hideController()
        } else {
            coroutineScope.launch {
                awaitFrame()
                onBackPressedDispatcher?.onBackPressed()
                backHandlerEnabled = false
            }
        }
    }

    Box(modifier = Modifier.background(color = Color.Black)) {
        AndroidView(
            modifier = Modifier
                .focusable()
                .onKeyEvent {
                    when (it.nativeKeyEvent.keyCode) {
                        KeyEvent.KEYCODE_DPAD_RIGHT -> {
                            playerView.player?.seekForward(); return@onKeyEvent true
                        }
                        KeyEvent.KEYCODE_DPAD_LEFT -> {
                            playerView.player?.seekBack(); return@onKeyEvent true
                        }
                        KeyEvent.KEYCODE_MEDIA_PLAY -> {
                            playerView.player?.play(); return@onKeyEvent true
                        }
                        KeyEvent.KEYCODE_MEDIA_PLAY_PAUSE -> {
                            playerView.player?.pause(); return@onKeyEvent true
                        }
                    }
                    playerView.dispatchKeyEvent(it.nativeKeyEvent)
                },
            factory = { playerView }
        )

        DisposableEffect(Unit) {
            onDispose {
                exoPlayer.release()
            }
        }
    }
}