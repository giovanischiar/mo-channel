package io.schiar.mochannel.library.retrofit

import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.ServerURLDataSource
import io.schiar.mochannel.model.datasource.TVShowsDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class TVShowsRetrofitDataSource(
    private val tvShowsRetrofitAPI: TVShowsRetrofitAPI,
    private val serverURLDataSource: ServerURLDataSource
): TVShowsDataSource {
     private suspend fun getTVShowsFrom(url: String): List<TVShow> = run {
        return (try {
            tvShowsRetrofitAPI.getTVShowsFrom(url = url)
        } catch (exception: Exception) {
            Response.success(emptyList())
        }.body() ?: emptyList()).toTVShows()
    }

    override suspend fun retrieve(): List<TVShow> {
        val serverURL = withContext(Dispatchers.IO) { serverURLDataSource.retrieve() }
        return getTVShowsFrom(url = serverURL?.toStringWithRoute(route = "tv-shows") ?: "")
    }
}