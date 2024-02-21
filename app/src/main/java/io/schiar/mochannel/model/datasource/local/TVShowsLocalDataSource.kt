package io.schiar.mochannel.model.datasource.local

import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.TVShowsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TVShowsLocalDataSource(private val tvShows: List<TVShow> = emptyList()): TVShowsDataSource {
    override fun retrieve(): Flow<List<TVShow>> = flow {
        emit(tvShows)
    }
}