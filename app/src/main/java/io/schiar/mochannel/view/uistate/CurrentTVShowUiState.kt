package io.schiar.mochannel.view.uistate

import androidx.compose.runtime.Immutable
import io.schiar.mochannel.view.viewdata.TVShowViewData

@Immutable
sealed interface CurrentTVShowUiState {
    data object Loading: CurrentTVShowUiState
    data class CurrentTVShowLoaded(val tvShow: TVShowViewData): CurrentTVShowUiState
}