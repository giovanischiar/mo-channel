package io.schiar.mochannel.model.datasource

import io.schiar.mochannel.model.TVShow
import kotlinx.coroutines.flow.Flow

interface TVShowsDataSource {
    fun retrieve(): Flow<List<TVShow>>
}