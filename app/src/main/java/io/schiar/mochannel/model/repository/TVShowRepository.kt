package io.schiar.mochannel.model.repository

import io.schiar.mochannel.model.Episode
import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.CurrentEpisodeURLsDataSource
import io.schiar.mochannel.model.datasource.CurrentTVShowDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class TVShowRepository @Inject constructor(
    currentTVShowDataSource: CurrentTVShowDataSource,
    private val currentEpisodeURLsDataSource: CurrentEpisodeURLsDataSource
) {
    private var _currentTVShow = TVShow(name = "", episodes = emptyList())
    val currentTVShow = currentTVShowDataSource.retrieve()
        .filterNotNull()
        .onEach { _currentTVShow = it }
    private val _currentEpisodesFromSeason = MutableStateFlow<List<Episode>>(emptyList())
    val currentEpisodesFromSeason: Flow<List<Episode>> = _currentEpisodesFromSeason

    fun selectEpisodesFromSeasonAt(index: Int) {
        _currentEpisodesFromSeason.update { _currentTVShow.episodesFromSeasonAt(index = index) }
    }

    fun urlsOfEpisodesFromIndexOfSeasonAt(index: Int, episodeIndex: Int) {
        currentEpisodeURLsDataSource.update(
            episodeURLs = _currentTVShow.urlsOfEpisodesFromIndexOfSeasonAt(
                index = index, episodeIndex = episodeIndex
            )
        )
    }
}