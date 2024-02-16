package io.schiar.mochannel.model.repository

import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.TVShowsDataSource
import io.schiar.mochannel.model.repository.listeners.CurrentTVShowListener
import io.schiar.mochannel.model.repository.listeners.ServerURLChangedOnDataSourceListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext

class TVShowsRepository(
    private val tvShowsDataSource: TVShowsDataSource,
    private val currentTVShowChangedListener: CurrentTVShowListener
): ServerURLChangedOnDataSourceListener {
    private var tvShows: List<TVShow>? = null
    private var tvShowsCallback: ((List<TVShow>) -> Unit)? = null

    fun subscribeForTVShows(callback: (tvShows: List<TVShow>) -> Unit) {
        tvShowsCallback = callback
    }

    suspend fun loadTVShows() {
        tvShows = withContext(Dispatchers.IO) { tvShowsDataSource.retrieve() }
        (tvShowsCallback ?: {})(tvShows ?: return)
    }

    override suspend fun serverURLChangedOnDataSource(): Unit = coroutineScope { loadTVShows() }

    fun selectTVShowAt(index: Int) {
        val tvShows = tvShows ?: return
        val tvShow = tvShows.getOrNull(index = index) ?: return
        currentTVShowChangedListener.currentTVShowChangedTo(newCurrentTVShow = tvShow)
    }
}