package io.schiar.mochannel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.schiar.mochannel.model.ServerURL
import io.schiar.mochannel.model.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppViewModel(settingsRepository: SettingsRepository): ViewModel() {
    private val _serverURL = MutableStateFlow(value = "")
    val serverURL: StateFlow<String> = _serverURL

    private fun onIsServerURLChange(serverURL: ServerURL) {
        _serverURL.update { serverURL.toString() }
    }

    init {
        settingsRepository.subscribeForServerURLs(::onIsServerURLChange)
        viewModelScope.launch { settingsRepository.loadServerURL() }
    }
}