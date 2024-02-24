package io.schiar.mochannel.library.retrofit

import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.ServerURLDataSource
import io.schiar.mochannel.model.datasource.TVShowsDataSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TVShowsRetrofitDataSource @Inject constructor(
    private val tvShowsRetrofitAPI: TVShowsRetrofitAPI,
    serverURLDataSource: ServerURLDataSource
): TVShowsDataSource {
    @OptIn(ExperimentalCoroutinesApi::class)
    private val tvShows = serverURLDataSource.retrieve()
        .filterNotNull()
        .flatMapLatest { getTVShowsFrom(url = it.toStringWithRoute(route = "tv-shows")) }

     private fun getTVShowsFrom(url: String): Flow<List<TVShow>> = flow {
         emit(tvShowsRetrofitAPI.getTVShowsFrom(url = url).toTVShows())
    }

    override fun retrieve(): Flow<List<TVShow>> {
        return tvShows
    }
}