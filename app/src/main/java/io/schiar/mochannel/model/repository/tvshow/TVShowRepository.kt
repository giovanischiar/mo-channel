package io.schiar.mochannel.model.repository.tvshow

import io.schiar.mochannel.model.TVShow

interface TVShowRepository {
    suspend fun loadTVShows(onTVShowsReady: (tvShows: List<TVShow>) -> Unit)
}