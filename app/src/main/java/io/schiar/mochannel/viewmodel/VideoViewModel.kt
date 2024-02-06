package io.schiar.mochannel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.schiar.mochannel.model.repository.MainRepository
import io.schiar.mochannel.model.repository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VideoViewModel(
    repository: VideoRepository = MainRepository()
) : ViewModel() {
    private val _urls = MutableStateFlow(emptyList<String>())
    val urls: StateFlow<List<String>> = _urls

    private fun onUrlsChanged(urls: List<String>) {
        _urls.update { urls }
    }

    init { repository.subscribeForCurrentTVShowUrls(::onUrlsChanged) }
}