package io.schiar.mochannel.library.retrofit

import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.service.ServerURLService
import io.schiar.mochannel.model.datasource.service.TVShowsService
import retrofit2.Response

class TVShowsRetrofitService(
    private val tvShowsRetrofitAPI: TVShowsRetrofitAPI,
    private val serverURLService: ServerURLService
): TVShowsService {
     private fun getTVShowsFrom(url: String): List<TVShow> {
        return (try {
            tvShowsRetrofitAPI.getTVShowsFrom(url = url)
        } catch (exception: Exception) {
            Response.success(emptyList())
        }.body() ?: emptyList()).toTVShows()
    }

    override fun retrieve(): List<TVShow> {
        val serverURL = serverURLService.retrieve()
        return getTVShowsFrom(url = serverURL?.toStringWithRoute(route = "tv-shows") ?: "")
    }
}