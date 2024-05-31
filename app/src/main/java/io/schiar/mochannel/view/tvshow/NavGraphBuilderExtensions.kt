package io.schiar.mochannel.view.tvshow

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.schiar.mochannel.view.tvshow.uistate.CurrentEpisodesFromSeasonUiState
import io.schiar.mochannel.view.tvshow.uistate.CurrentTVShowUiState
import io.schiar.mochannel.viewmodel.TVShowViewModel

fun NavGraphBuilder.tvShowScreen(
    tvShowViewModel: TVShowViewModel? = null,
    onNavigateToVideo: () -> Unit
) {
    composable(route = "TVShow") {
        val viewModel = tvShowViewModel ?: hiltViewModel()
        val currentTVShowUiState by viewModel
            .currentTVShowUiStateFlow
            .collectAsState(initial = CurrentTVShowUiState.Loading)

        val currentEpisodesFromSeasonUiState by viewModel
            .currentEpisodesFromSeasonUiStateFlow
            .collectAsState(initial = CurrentEpisodesFromSeasonUiState.Loading)

        TVShowScreen(
            currentTVShowUiState = currentTVShowUiState,
            currentEpisodesFromSeasonUiState = currentEpisodesFromSeasonUiState,
            selectEpisodeOnIndexFromSeasonAt = viewModel::selectEpisodeOnIndexFromSeasonAt,
            selectEpisodesFromSeasonAt = viewModel::selectEpisodesFromSeasonAt,
            onVideoPressed = onNavigateToVideo
        )
    }
}