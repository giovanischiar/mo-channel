package io.schiar.mochannel.model.datasource.tvshow

import io.schiar.mochannel.model.ServerURL
import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.ServerURLRetriever
import io.schiar.mochannel.model.datasource.tvshow.api.TVShowsAPI
import io.schiar.mochannel.model.datasource.tvshow.util.TVShowsLocalAPI
import io.schiar.mochannel.model.datasource.tvshow.util.toTVShows
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class TVShowDataSource(
    private val tvShowsAPI: TVShowsAPI = TVShowsLocalAPI(),
    private val serverURLRetriever: ServerURLRetriever = object: ServerURLRetriever {
        override suspend fun retrieveServerURL(): ServerURL {
            return ServerURL()
        }
    }
): TVShowDataSourceable {
    private var tvShows: List<TVShow>? = null
    private var serverURL: String = ""
    private var currentTVShowIndex: Int = -1

    override suspend fun retrieveTVShows(): List<TVShow> {
        val serverURL = serverURLRetriever.retrieveServerURL().toString()
        return if (tvShows == null || this.serverURL != serverURL) {
            this.serverURL = serverURL
            val result = withContext(Dispatchers.IO) {
                try {
                    tvShowsAPI.getTVShows(url = serverURL)
                } catch (exception: Exception) {
                    Response.success(emptyList())
                }
            }
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