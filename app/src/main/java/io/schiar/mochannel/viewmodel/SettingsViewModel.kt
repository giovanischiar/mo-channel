package io.schiar.mochannel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.schiar.mochannel.model.repository.SettingsRepository
import io.schiar.mochannel.view.settings.ServerURLUiState
import io.schiar.mochannel.viewmodel.util.toViewData
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository
): ViewModel() {
    val serverURLUiStateFlow = settingsRepository.serverURL
        .map { ServerURLUiState.ServerURLLoaded(it.toViewData()) }

    fun updatePrefixTo(newPrefix: String) = viewModelScope.launch {
        settingsRepository.updatePrefixTo(newPrefix = newPrefix)
    }

    fun updateURLTo(newURL: String) = viewModelScope.launch {
        settingsRepository.updateURLTo(newURL = newURL)
    }

    fun updatePortTo(newPort: String) = viewModelScope.launch {
        settingsRepository.updatePortTo(newPort = newPort)
    }
}