package io.schiar.mochannel.view.screen

import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.annotation.OptIn
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import io.schiar.mochannel.viewmodel.VideoViewModel
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.launch

@OptIn(UnstableApi::class) @Composable
fun VideoScreen(videoViewModel: VideoViewModel) {
    val context = LocalContext.current
    val onBackPressedDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher
    val coroutineScope = rememberCoroutineScope()
    val exoPlayer = remember { ExoPlayer.Builder(context).build().apply { prepare() } }
    val urls by videoViewModel.source.collectAsState()
    var backHandlerEnabled by remember { mutableStateOf(value = true) }
    videoViewModel.loadEpisode()

    LaunchedEffect(urls) {
        exoPlayer.setMediaItems(urls.map { url -> MediaItem.fromUri(Uri.parse(url)) })
        exoPlayer.playWhenReady = true
    }

    val playerView = remember {
        PlayerView(context).apply {
            player = exoPlayer
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

    Box {
        AndroidView(
            modifier = Modifier
                .focusable()
                .onKeyEvent { playerView.dispatchKeyEvent(it.nativeKeyEvent) },
            factory = { playerView }
        )

        DisposableEffect(Unit) {
            onDispose {
                exoPlayer.release()
            }
        }
    }
}