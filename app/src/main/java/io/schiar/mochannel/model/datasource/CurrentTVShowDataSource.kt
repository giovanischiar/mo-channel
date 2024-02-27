package io.schiar.mochannel.model.datasource

import io.schiar.mochannel.model.TVShow
import kotlinx.coroutines.flow.Flow

interface CurrentTVShowDataSource {
    fun retrieve(): Flow<TVShow?>
    fun update(tvShow: TVShow)
}