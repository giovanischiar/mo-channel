package io.schiar.mochannel.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.schiar.mochannel.model.repository.VideoRepository
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(videoRepository: VideoRepository) : ViewModel() {
    val urls = videoRepository.currentTVShowEpisodeURLs
}