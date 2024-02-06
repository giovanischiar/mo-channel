package io.schiar.mochannel.model.datasource

import io.schiar.mochannel.model.TVShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TVShowLocalHostDataSource: TVShowDataSourceable {
    private var tvShows: List<TVShow>? = null
    private var currentTVShowIndex: Int = -1

    override suspend fun retrieveTVShows(): List<TVShow> {
        return if (tvShows == null) {
            val api = RetrofitHelper.getInstance().create(Api::class.java)
            val result = withContext(Dispatchers.IO) { api.getTVShows() }
            tvShows = result.body()
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