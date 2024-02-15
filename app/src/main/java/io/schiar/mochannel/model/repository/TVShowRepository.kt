package io.schiar.mochannel.model.repository

import io.schiar.mochannel.model.Episode
import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.tvshow.TVShowDataSource
import io.schiar.mochannel.model.repository.listeners.CurrentEpisodeURLsListener
import io.schiar.mochannel.model.repository.listeners.CurrentTVShowListener

class TVShowRepository(
    private val tvShowDataSource: TVShowDataSource,
    private val currentEpisodeURLsListener: CurrentEpisodeURLsListener
): CurrentTVShowListener {
    private var currentTVShowCallback: ((TVShow) -> Unit)? = null
    private var currentEpisodesFromSeasonCallback: ((List<Episode>) -> Unit)? = null

    fun subscribeForCurrentTVShow(callback: (tvShow: TVShow) -> Unit) {
        currentTVShowCallback = callback
    }

    fun subscribeForCurrentEpisodesFromSeason(callback: (episodes: List<Episode>) -> Unit) {
        currentEpisodesFromSeasonCallback = callback
    }

    suspend fun selectEpisodesFromSeasonAt(index: Int) {
        val currentTVShow = tvShowDataSource.retrieveCurrentTVShow() ?: return
        (currentEpisodesFromSeasonCallback ?: {})(
            currentTVShow.episodesFromSeasonAt(index = index)
        )
    }

    suspend fun urlsOfEpisodesFromIndexOfSeasonAt(index: Int, episodeIndex: Int) {
        val currentTVShow = tvShowDataSource.retrieveCurrentTVShow() ?: return
        val urls = currentTVShow.urlsOfEpisodesFromIndexOfSeasonAt(
            index = index, episodeIndex = episodeIndex
        )
        currentEpisodeURLsListener.onCurrentEpisodeURLsListener(urls = urls)
    }

    override fun onCurrentTVShow(tvShow: TVShow) {
        (currentTVShowCallback ?: {})(tvShow)
    }
}