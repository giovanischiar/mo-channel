package io.schiar.mochannel.model.datasource.tvshow

import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.tvshow.requester.TVShowsLocalRequester
import io.schiar.mochannel.model.datasource.tvshow.requester.TVShowsRequester
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TVShowDataSource(
    private val tvShowsRequester: TVShowsRequester = TVShowsLocalRequester()
): TVShowDataSourceable {
    private var currentTVShowIndex: Int = -1

    override suspend fun retrieveTVShows(): List<TVShow> {
        return withContext(Dispatchers.IO) { tvShowsRequester.requestTVShows() }
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