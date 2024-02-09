package io.schiar.mochannel.model.datasource.tvshow.requester

import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.settings.requester.ServerURLMemoryRequester
import io.schiar.mochannel.model.datasource.settings.requester.ServerURLRequester

class TVShowsLocalRequester(
    private val tvShows: List<TVShow> = emptyList(),
    serverURLRequester: ServerURLRequester = ServerURLMemoryRequester()
): TVShowsRequester(serverURLRequester) {
    override suspend fun getTVShowsFrom(url: String): List<TVShow> {
        return tvShows
    }
}