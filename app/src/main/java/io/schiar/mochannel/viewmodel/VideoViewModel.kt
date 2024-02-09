package io.schiar.mochannel.viewmodel

import androidx.lifecycle.ViewModel
import io.schiar.mochannel.model.repository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class VideoViewModel(videoRepository: VideoRepository) : ViewModel() {
    private val _urls = MutableStateFlow(emptyList<String>())
    val urls: StateFlow<List<String>> = _urls

    private fun onUrlsChanged(urls: List<String>) {
        _urls.update { urls }
    }

    init { videoRepository.subscribeForCurrentTVShowUrls(::onUrlsChanged) }
}