package io.schiar.mochannel.model.datasource

import kotlinx.coroutines.flow.Flow

interface CurrentEpisodeURLsDataSource {
    fun retrieve(): Flow<List<String>>
    fun update(episodeURLs: List<String>)
}