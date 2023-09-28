package io.schiar.mochannel.model.repository.tvshow

import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.TVShowDataSource
import io.schiar.mochannel.model.datasource.TVShowLocalHostDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TVShowAPIRepository(
    private val tvShowDataSource: TVShowDataSource = TVShowLocalHostDataSource()
): TVShowRepository {
    override suspend fun loadTVShows(onTVShowsReady: (tvShows: List<TVShow>) -> Unit) {
        coroutineScope {
            launch(Dispatchers.IO) {
                val tvShows = withContext(Dispatchers.IO) {
                    tvShowDataSource.fetchTVShows()
                }
                onTVShowsReady(tvShows)
            }
        }
    }
}