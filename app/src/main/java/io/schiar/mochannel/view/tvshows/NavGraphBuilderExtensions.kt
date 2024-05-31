package io.schiar.mochannel.view.tvshows

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.schiar.mochannel.viewmodel.TVShowsViewModel

fun NavGraphBuilder.tvShowsScreen(
    onNavigateToSettings: () -> Unit,
    onNavigateToTVShow: () -> Unit,
    tvShowsViewModel: TVShowsViewModel? = null
) {
    composable(route = "TVShows") {
        val viewModel = tvShowsViewModel ?: hiltViewModel()
        val tvShowsUiState by viewModel
            .tvShowsUiStateFlow
            .collectAsState(initial = TVShowsUiState.Loading)

        TVShowsScreen(
            tvShowsUiState = tvShowsUiState,
            selectTVShowAt = viewModel::selectTVShowAt,
            onSettingsPressed = onNavigateToSettings,
            onTVShowPressed = onNavigateToTVShow
        )
    }
}