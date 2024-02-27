package io.schiar.mochannel.model.datasource.local

import io.schiar.mochannel.model.datasource.CurrentEpisodeURLsDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class CurrentEpisodeURLsLocalDataSource @Inject constructor(): CurrentEpisodeURLsDataSource {
    private val currentEpisodeURLs = MutableStateFlow(value = emptyList<String>())

    override fun retrieve(): Flow<List<String>> { return currentEpisodeURLs }
    override fun update(episodeURLs: List<String>) { currentEpisodeURLs.update { episodeURLs } }
}