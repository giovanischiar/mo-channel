package io.schiar.mochannel.model.repository

import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.TVShowsDataSource
import io.schiar.mochannel.model.repository.listeners.CurrentTVShowListener
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach

class TVShowsRepository(
    tvShowsDataSource: TVShowsDataSource,
    private val currentTVShowChangedListener: CurrentTVShowListener
) {
    private var _tvShows: List<TVShow> = emptyList()

    val tvShows = tvShowsDataSource.retrieve()
        .onEach { if (it.isNotEmpty()) { _tvShows = it  }}
        .catch { emit(_tvShows); throw it }

    fun selectTVShowAt(index: Int) {
        val tvShow = _tvShows.getOrNull(index = index) ?: return
        currentTVShowChangedListener.currentTVShowChangedTo(newCurrentTVShow = tvShow)
    }
}