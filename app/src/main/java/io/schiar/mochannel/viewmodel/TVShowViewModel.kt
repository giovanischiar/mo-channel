package io.schiar.mochannel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.schiar.mochannel.model.Episode
import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.repository.TVShowRepository
import io.schiar.mochannel.view.viewdata.EpisodeViewData
import io.schiar.mochannel.view.viewdata.TVShowViewData
import io.schiar.mochannel.viewmodel.util.toViewData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TVShowViewModel(private val tvShowRepository: TVShowRepository) : ViewModel() {
    private val _currentTVShow = MutableStateFlow<TVShowViewData?>(value = null)
    val currentTVShow: StateFlow<TVShowViewData?> = _currentTVShow
    private val _currentEpisodesFromSeason = MutableStateFlow<List<EpisodeViewData>>(
        value = emptyList()
    )
    val currentEpisodesFromSeason: StateFlow<List<EpisodeViewData>> = _currentEpisodesFromSeason

    private fun onCurrentTVShowChanged(tvShow: TVShow) {
        _currentTVShow.update { tvShow.toViewData() }
    }

    private fun onCurrentEpisodesFromSeasonChanged(episodes: List<Episode>) {
        _currentEpisodesFromSeason.update { episodes.map { it.toViewData() } }
    }

    init {
        tvShowRepository.subscribeForCurrentTVShow(::onCurrentTVShowChanged)
        tvShowRepository.subscribeForCurrentEpisodesFromSeason(::onCurrentEpisodesFromSeasonChanged)
    }

    fun selectEpisodesFromSeasonAt(index: Int) = viewModelScope.launch {
        tvShowRepository.selectEpisodesFromSeasonAt(index = index)
    }

    fun selectEpisodeOnIndexFromSeasonAt(index: Int, episodeIndex: Int) = viewModelScope.launch {
        tvShowRepository.urlsOfEpisodesFromIndexOfSeasonAt(
            index = index, episodeIndex = episodeIndex
        )
    }
}