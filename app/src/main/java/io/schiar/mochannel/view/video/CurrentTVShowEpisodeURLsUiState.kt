package io.schiar.mochannel.view.video

import androidx.compose.runtime.Immutable

@Immutable
sealed interface CurrentTVShowEpisodeURLsUiState {
    data object Loading: CurrentTVShowEpisodeURLsUiState
    data class CurrentEpisodesFromSeasonLoaded(
        val urls: List<String>
    ): CurrentTVShowEpisodeURLsUiState
}