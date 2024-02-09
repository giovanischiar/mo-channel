package io.schiar.mochannel.model.datasource.tvshow.requester

import io.schiar.mochannel.library.retrofit.TVShowsServerAPI
import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.settings.requester.ServerURLRequester
import retrofit2.Response

class TVShowsServerRequester(
    private val tvShowsServerAPI: TVShowsServerAPI, serverURLRequester: ServerURLRequester
): TVShowsRequester(serverURLRequester) {
    override suspend fun getTVShowsFrom(url: String): List<TVShow> {
        return (try {
            tvShowsServerAPI.getTVShowsFrom(url = url)
        } catch (exception: Exception) {
            Response.success(emptyList())
        }.body() ?: emptyList()).toTVShows()
    }
}