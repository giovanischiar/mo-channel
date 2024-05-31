package io.schiar.mochannel.view.tvshows

import androidx.compose.runtime.Immutable
import io.schiar.mochannel.view.viewdata.TVShowViewData

@Immutable
sealed interface TVShowsUiState {
    data object Loading: TVShowsUiState
    data class Error(val message: String): TVShowsUiState
    data class TVShowsLoaded(val tvShows: List<TVShowViewData>): TVShowsUiState
}