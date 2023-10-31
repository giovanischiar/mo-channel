package io.schiar.mochannel.model.repository

import io.schiar.mochannel.model.TVShow

interface TVShowsRepository {
    suspend fun loadTVShows(onTVShowsLoaded: (tvShows: List<TVShow>) -> Unit)
    fun selectTVShow(name: String)
}