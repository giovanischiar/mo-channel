package io.schiar.mochannel.model.repository

import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.CurrentTVShowDataSource
import io.schiar.mochannel.model.datasource.TVShowsDataSource
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class TVShowsRepository @Inject constructor(
    tvShowsDataSource: TVShowsDataSource,
    private val currentTVShowDataSource: CurrentTVShowDataSource,
) {
    private var _tvShows: List<TVShow> = emptyList()
    val tvShows = tvShowsDataSource.retrieve()
        .onEach { if (it.isNotEmpty()) { _tvShows = it  }}
        .catch { emit(_tvShows); throw it }

    fun selectTVShowAt(index: Int) {
        val tvShow = _tvShows.getOrNull(index = index) ?: return
        currentTVShowDataSource.update(tvShow)
    }
}