package io.schiar.mochannel.model.datasource

import io.schiar.mochannel.model.TVShow

interface TVShowDataSource {
    suspend fun fetchTVShows():List<TVShow>
}