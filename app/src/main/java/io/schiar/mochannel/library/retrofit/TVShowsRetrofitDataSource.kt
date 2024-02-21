package io.schiar.mochannel.library.retrofit

import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.ServerURLDataSource
import io.schiar.mochannel.model.datasource.TVShowsDataSource
import kotlinx.coroutines.flow.first

class TVShowsRetrofitDataSource(
    private val tvShowsRetrofitAPI: TVShowsRetrofitAPI,
    private val serverURLDataSource: ServerURLDataSource
): TVShowsDataSource {
     private suspend fun getTVShowsFrom(url: String): List<TVShow> = run {
        return tvShowsRetrofitAPI.getTVShowsFrom(url = url).body()?.toTVShows() ?: emptyList()
    }

    override suspend fun retrieve(): List<TVShow> {
        val serverURL = serverURLDataSource.retrieve().first()
        return getTVShowsFrom(url = serverURL?.toStringWithRoute(route = "tv-shows") ?: "")
    }
}