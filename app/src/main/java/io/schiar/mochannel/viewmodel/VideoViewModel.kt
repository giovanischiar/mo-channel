package io.schiar.mochannel.viewmodel

import androidx.lifecycle.ViewModel
import io.schiar.mochannel.model.repository.EpisodeRepository
import io.schiar.mochannel.model.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class VideoViewModel(
    private val repository: EpisodeRepository = MainRepository()
) : ViewModel() {
    private var url =
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
    private val _source = MutableStateFlow(url)
    val source: StateFlow<String> = _source

    fun loadEpisode() {
        _source.update { repository.currentEpisodeURL }
    }
}