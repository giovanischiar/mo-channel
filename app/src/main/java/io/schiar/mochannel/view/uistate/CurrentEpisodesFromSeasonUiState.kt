package io.schiar.mochannel.view.uistate

import androidx.compose.runtime.Immutable
import io.schiar.mochannel.view.viewdata.EpisodeViewData

@Immutable
sealed interface CurrentEpisodesFromSeasonUiState {
    data object Loading: CurrentEpisodesFromSeasonUiState
    data class CurrentEpisodesFromSeasonLoaded(
        val episodes: List<EpisodeViewData>
    ): CurrentEpisodesFromSeasonUiState
}