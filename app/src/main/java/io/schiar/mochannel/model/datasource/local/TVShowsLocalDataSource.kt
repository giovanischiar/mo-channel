package io.schiar.mochannel.model.datasource.local

import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.TVShowsDataSource

class TVShowsLocalDataSource(private val tvShows: List<TVShow> = emptyList()): TVShowsDataSource {
    override suspend fun retrieve(): List<TVShow> {
        return tvShows
    }
}