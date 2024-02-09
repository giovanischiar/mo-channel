package io.schiar.mochannel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.schiar.mochannel.model.ServerURL
import io.schiar.mochannel.model.repository.SettingsRepository
import io.schiar.mochannel.view.viewdata.ServerURLViewData
import io.schiar.mochannel.viewmodel.util.toViewData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(private val settingsRepository: SettingsRepository): ViewModel() {
    private val _serverURL = MutableStateFlow<ServerURLViewData?>(value = null)
    val serverURL: StateFlow<ServerURLViewData?> = _serverURL

    private fun onServerURLChanged(serverURL: ServerURL) {
        _serverURL.update { serverURL.toViewData() }
    }

    init {
        settingsRepository.subscribeForServerURLs(::onServerURLChanged)
        viewModelScope.launch { settingsRepository.loadServerURL() }
    }

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