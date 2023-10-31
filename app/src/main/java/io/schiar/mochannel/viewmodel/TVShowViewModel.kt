package io.schiar.mochannel.viewmodel

import androidx.lifecycle.ViewModel
import io.schiar.mochannel.model.Episode
import io.schiar.mochannel.model.repository.EpisodesRepository
import io.schiar.mochannel.model.repository.MainRepository
import io.schiar.mochannel.view.viewdata.EpisodeViewData
import io.schiar.mochannel.viewmodel.util.toViewData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class TVShowViewModel(
    private val repository: EpisodesRepository = MainRepository()
) : ViewModel() {
    private val _episodes = MutableStateFlow(emptyList<EpisodeViewData>())
    val episodes: StateFlow<List<EpisodeViewData>> = _episodes
    private val _currentTVShow = MutableStateFlow(repository.currentTVShow)
    val currentTVShow: StateFlow<String> = _currentTVShow

    fun loadEpisodes() {
        val episodes = repository.loadEpisodes()
        _episodes.update { episodes.map { episode: Episode -> episode.toViewData() } }
        _currentTVShow.update { repository.currentTVShow }
    }

    fun selectEpisode(url: String) {
        repository.selectEpisode(url = url)
    }
}