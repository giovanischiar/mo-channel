package io.schiar.mochannel.model.repository

import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.tvshow.TVShowDataSourceable
import io.schiar.mochannel.model.repository.listeners.CurrentTVShowListener
import io.schiar.mochannel.model.repository.listeners.ServerURLChangedListener

class TVShowsRepository(
    private val tvShowDataSourceable: TVShowDataSourceable,
    private val currentTVShowChangedListener: CurrentTVShowListener
): ServerURLChangedListener {
    private var tvShowsCallback: ((List<TVShow>) -> Unit)? = null

    fun subscribeForTVShows(callback: (tvShows: List<TVShow>) -> Unit) {
        tvShowsCallback = callback
    }

    override suspend fun onServerUrlChanged() {
        val tvShows = tvShowDataSourceable.retrieveTVShows()
        tvShowsCallback?.let { it(tvShows) }
    }

    suspend fun selectTVShowAt(index: Int) {
        val tvShow = tvShowDataSourceable.retrieveTVShowAt(index = index) ?: return
        currentTVShowChangedListener.onCurrentTVShow(tvShow = tvShow)
    }
}