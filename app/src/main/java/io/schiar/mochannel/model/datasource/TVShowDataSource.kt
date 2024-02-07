package io.schiar.mochannel.model.datasource

import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.api.TVShowsAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TVShowDataSource(
    private val tvShowsAPI: TVShowsAPI = TVShowsLocalAPI()
): TVShowDataSourceable {
    private var tvShows: List<TVShow>? = null
    private var currentTVShowIndex: Int = -1

    override suspend fun retrieveTVShows(): List<TVShow> {
        return if (tvShows == null) {
            val result = withContext(Dispatchers.IO) { tvShowsAPI.getTVShows() }
            tvShows = result.body()?.toTVShows()
            tvShows ?: emptyList()
        } else { tvShows ?: emptyList() }
    }

    override suspend fun retrieveTVShowAt(index: Int): TVShow? {
        currentTVShowIndex = index
        val tvShows = retrieveTVShows()
        return tvShows.getOrNull(index = index)
    }

    override suspend fun retrieveCurrentTVShow(): TVShow? {
        val tvShows = retrieveTVShows()
        return tvShows.getOrNull(index = currentTVShowIndex)
    }
}