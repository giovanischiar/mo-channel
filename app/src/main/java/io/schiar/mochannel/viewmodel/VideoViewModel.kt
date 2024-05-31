package io.schiar.mochannel.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.schiar.mochannel.model.repository.VideoRepository
import io.schiar.mochannel.view.video.CurrentTVShowEpisodeURLsUiState
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class VideoViewModel @Inject constructor(videoRepository: VideoRepository) : ViewModel() {
    val urlsUiStateFlow = videoRepository.currentTVShowEpisodeURLs.map {
        CurrentTVShowEpisodeURLsUiState.CurrentEpisodesFromSeasonLoaded(it)
    }
}