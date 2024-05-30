package io.schiar.mochannel.view.uistate

import androidx.compose.runtime.Immutable
import io.schiar.mochannel.view.viewdata.ServerURLViewData

@Immutable
sealed interface ServerURLUiState {
    data object Loading: ServerURLUiState
    data class ServerURLLoaded(val serverURL: ServerURLViewData): ServerURLUiState
}