package io.schiar.mochannel.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.schiar.mochannel.model.repository.SettingsRepository
import io.schiar.mochannel.viewmodel.util.toViewData
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SettingsViewModel(private val settingsRepository: SettingsRepository): ViewModel() {
    val serverURL = settingsRepository.serverURL.map { it.toViewData() }
        .onEach { Log.d("mo channel debug", "viewmodel.SettingsViewModel serverURL: $it") }

    fun updatePrefixTo(newPrefix: String) = viewModelScope.launch {
        settingsRepository.updatePrefixTo(newPrefix = newPrefix)
    }

    fun updateURLTo(newURL: String) = viewModelScope.launch {
        Log.d("mo channel debug", "settingsRepository.updateURLTo(newURL = $newURL)")
        settingsRepository.updateURLTo(newURL = newURL)
    }

    fun updatePortTo(newPort: String) = viewModelScope.launch {
        settingsRepository.updatePortTo(newPort = newPort)
    }

    fun submitServerURL() = viewModelScope.launch {
        settingsRepository.serverURLChanged()
    }
}