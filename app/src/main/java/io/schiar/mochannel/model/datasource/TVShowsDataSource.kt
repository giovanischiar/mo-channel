package io.schiar.mochannel.model.datasource

import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.service.local.TVShowsLocalService
import io.schiar.mochannel.model.datasource.service.TVShowsService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TVShowsDataSource(private val tvShowsService: TVShowsService = TVShowsLocalService()) {
    private var currentTVShowIndex: Int = -1

    suspend fun retrieveTVShows(): List<TVShow> {
        return withContext(Dispatchers.IO) { tvShowsService.retrieve() }
    }

    suspend fun retrieveTVShowAt(index: Int): TVShow? {
        currentTVShowIndex = index
        val tvShows = retrieveTVShows()
        return tvShows.getOrNull(index = index)
    }

    suspend fun retrieveCurrentTVShow(): TVShow? {
        val tvShows = retrieveTVShows()
        return tvShows.getOrNull(index = currentTVShowIndex)
    }
}