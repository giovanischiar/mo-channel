package io.schiar.mochannel.model.repository

import io.schiar.mochannel.model.datasource.CurrentEpisodeURLsDataSource
import javax.inject.Inject

class VideoRepository @Inject constructor(
    currentEpisodeURLsDataSource: CurrentEpisodeURLsDataSource
) {
    val currentTVShowEpisodeURLs = currentEpisodeURLsDataSource.retrieve()
}