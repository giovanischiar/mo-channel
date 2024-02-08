package io.schiar.mochannel.model.datasource.tvshow

import io.schiar.mochannel.model.TVShow

interface TVShowDataSourceable {
    suspend fun retrieveTVShows():List<TVShow>
    suspend fun retrieveTVShowAt(index: Int): TVShow?
    suspend fun retrieveCurrentTVShow(): TVShow?
}