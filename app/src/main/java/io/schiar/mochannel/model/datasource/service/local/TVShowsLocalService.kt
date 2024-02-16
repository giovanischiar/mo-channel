package io.schiar.mochannel.model.datasource.service.local

import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.service.TVShowsService

class TVShowsLocalService(private val tvShows: List<TVShow> = emptyList()): TVShowsService {
    override fun retrieve(): List<TVShow> {
        return tvShows
    }
}