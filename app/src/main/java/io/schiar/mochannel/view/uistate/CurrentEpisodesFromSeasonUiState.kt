package io.schiar.mochannel.view.uistate

import androidx.compose.runtime.Immutable
import io.schiar.mochannel.model.Episode

@Immutable
sealed interface CurrentEpisodesFromSeasonUiState {
    data object Loading: CurrentEpisodesFromSeasonUiState
    data class CurrentEpisodesFromSeasonLoaded(
        val episodes: List<Episode>
    ): CurrentEpisodesFromSeasonUiState
}