package io.schiar.mochannel.model.datasource.tvshow.requester

import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.settings.requester.ServerURLRequester
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class TVShowsRequester(private val serverURLRequester: ServerURLRequester) {
    abstract suspend fun getTVShowsFrom(url: String): List<TVShow>

    suspend fun requestTVShows(): List<TVShow> {
        val serverURL = withContext(Dispatchers.IO) { serverURLRequester.requestServerURL() }
        return getTVShowsFrom(
            url = serverURL?.toStringWithRoute(route = "tv-shows") ?: ""
        )
    }
}