package io.schiar.mochannel.model.datasource

import io.schiar.mochannel.model.TVShow

interface TVShowsDataSource {
    suspend fun retrieve(): List<TVShow>
}