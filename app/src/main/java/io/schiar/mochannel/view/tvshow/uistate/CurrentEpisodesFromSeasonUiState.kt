package io.schiar.mochannel.view.tvshow.uistate

import androidx.compose.runtime.Immutable
import io.schiar.mochannel.view.shared.viewdata.EpisodeViewData

@Immutable
sealed interface CurrentEpisodesFromSeasonUiState {
    data object Loading: CurrentEpisodesFromSeasonUiState
    data class CurrentEpisodesFromSeasonLoaded(
        val episodes: List<EpisodeViewData>
    ): CurrentEpisodesFromSeasonUiState
}