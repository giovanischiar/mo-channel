package io.schiar.mochannel.view.video

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.schiar.mochannel.view.shared.util.Route
import io.schiar.mochannel.viewmodel.VideoViewModel

fun NavGraphBuilder.videoScreen(videoViewModel: VideoViewModel? = null) {
    composable(route = Route.VIDEO.id) {
        val viewModel = videoViewModel ?: hiltViewModel()
        val currentTVShowEpisodeURLsUiState by viewModel
            .urlsUiStateFlow
            .collectAsState(initial = CurrentTVShowEpisodeURLsUiState.Loading)
        VideoScreen(currentTVShowEpisodeURLsUiState = currentTVShowEpisodeURLsUiState)
    }
}