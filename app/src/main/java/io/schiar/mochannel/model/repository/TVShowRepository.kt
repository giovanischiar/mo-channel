package io.schiar.mochannel.model.repository

import io.schiar.mochannel.model.Episode
import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.repository.listeners.CurrentEpisodeURLsListener
import io.schiar.mochannel.model.repository.listeners.CurrentTVShowListener
import kotlin.properties.Delegates.observable

class TVShowRepository(
    private val currentEpisodeURLsListener: CurrentEpisodeURLsListener
): CurrentTVShowListener {
    private var currentTVShowCallback: ((TVShow) -> Unit)? = null
    private var currentEpisodesFromSeasonCallback: ((List<Episode>) -> Unit)? = null
    private var currentTVShow: TVShow? by observable(initialValue = null)
    { _, _, newCurrentTVShow ->
        if (newCurrentTVShow != null) { (currentTVShowCallback ?: {})(newCurrentTVShow) }
    }
    private var currentSeasonEpisodes: List<Episode> by observable(initialValue = emptyList())
    { _, _, newCurrentSeasonEpisodes ->
        if (newCurrentSeasonEpisodes.isNotEmpty()) {
            (currentEpisodesFromSeasonCallback ?: {})(newCurrentSeasonEpisodes)
        }
    }

    fun subscribeForCurrentTVShow(callback: (tvShow: TVShow) -> Unit) {
        currentTVShowCallback = callback
    }

    fun subscribeForCurrentEpisodesFromSeason(callback: (episodes: List<Episode>) -> Unit) {
        currentEpisodesFromSeasonCallback = callback
    }

    fun selectEpisodesFromSeasonAt(index: Int) {
        val currentTVShow = currentTVShow ?: return
        currentSeasonEpisodes = currentTVShow.episodesFromSeasonAt(index = index)
    }

    fun urlsOfEpisodesFromIndexOfSeasonAt(index: Int, episodeIndex: Int) {
        val currentTVShow = currentTVShow ?: return
        currentEpisodeURLsListener.currentEpisodeURLsChangedTo(
            newEpisodeURLs = currentTVShow.urlsOfEpisodesFromIndexOfSeasonAt(
                index = index, episodeIndex = episodeIndex
            )
        )
    }

    override fun currentTVShowChangedTo(newCurrentTVShow: TVShow) {
        currentTVShow = newCurrentTVShow
    }
}