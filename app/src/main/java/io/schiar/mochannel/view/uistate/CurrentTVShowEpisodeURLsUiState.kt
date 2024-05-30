package io.schiar.mochannel.view.uistate

import androidx.compose.runtime.Immutable

@Immutable
sealed interface CurrentTVShowEpisodeURLsUiState {
    data object Loading: CurrentTVShowEpisodeURLsUiState
    data class CurrentEpisodesFromSeasonLoaded(
        val urls: List<String>
    ): CurrentTVShowEpisodeURLsUiState
}