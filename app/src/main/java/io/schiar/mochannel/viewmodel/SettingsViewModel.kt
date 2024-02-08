package io.schiar.mochannel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.schiar.mochannel.model.ServerURL
import io.schiar.mochannel.model.repository.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(private val repository: SettingsRepository): ViewModel() {
    private val _prefix = MutableStateFlow(value = "")
    val prefix: StateFlow<String> = _prefix
    private val _url = MutableStateFlow(value = "")
    val url: StateFlow<String> = _url
    private val _port = MutableStateFlow(value = "")
    val port: StateFlow<String> = _port

    private fun onServerURLChanged(serverURL: ServerURL) {
        val (prefix, url, port) = serverURL
        _prefix.update { prefix }
        _url.update { url }
        _port.update { port }
    }

    init {
        repository.subscribeForServerURLs(::onServerURLChanged)
        viewModelScope.launch { repository.loadServerURL() }
    }

    fun updatePrefixTo(newPrefix: String) {
        viewModelScope.launch { repository.updatePrefixTo(newPrefix = newPrefix) }
    }

    fun updateURLTo(newURL: String) {
        viewModelScope.launch { repository.updateURLTo(newURL = newURL) }
    }

    fun updatePortTo(newPort: String) {
        viewModelScope.launch { repository.updatePortTo(newPort = newPort) }
    }
}