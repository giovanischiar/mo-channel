package io.schiar.mochannel.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.schiar.mochannel.model.repository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(videoRepository: VideoRepository) : ViewModel() {
    private val _urls = MutableStateFlow(emptyList<String>())
    val urls: StateFlow<List<String>> = _urls

    private fun onUrlsChanged(urls: List<String>) {
        _urls.update { urls }
    }

    init { videoRepository.subscribeForCurrentTVShowUrls(::onUrlsChanged) }
}