package io.schiar.mochannel.view.tvshow.uistate

import androidx.compose.runtime.Immutable
import io.schiar.mochannel.view.shared.viewdata.TVShowViewData

@Immutable
sealed interface CurrentTVShowUiState {
    data object Loading: CurrentTVShowUiState
    data class CurrentTVShowLoaded(val tvShow: TVShowViewData): CurrentTVShowUiState
}