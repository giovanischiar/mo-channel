package io.schiar.mochannel.model.repository

import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.TVShowDataSourceable
import io.schiar.mochannel.model.datasource.TVShowLocalHostDataSource

class MainRepository(
    private val tvShowDataSourceable: TVShowDataSourceable = TVShowLocalHostDataSource()
) : TVShowsRepository, TVShowRepository, VideoRepository {
    private var tvShowsCallback: ((List<TVShow>) -> Unit)? = null
    private var currentTVShowCallback: ((TVShow) -> Unit)? = null
    private var currentTVShowEpisodeUrlsCallback: ((List<String>) -> Unit)? = null

    // TVShowsRepository

    override fun subscribeForTVShows(callback: (tvShows: List<TVShow>) -> Unit) {
        tvShowsCallback = callback
    }

    override suspend fun loadTVShows() {
        val tvShows = tvShowDataSourceable.retrieveTVShows()
        tvShowsCallback?.let { it(tvShows) }
    }

    override suspend fun selectTVShowAt(index: Int) {
        val tvShow = tvShowDataSourceable.retrieveTVShowAt(index = index) ?: return
        currentTVShowCallback?.let { it(tvShow) }
    }

    // TVShowRepository

    override fun subscribeForCurrentTVShow(callback: (tvShow: TVShow) -> Unit) {
        currentTVShowCallback = callback
    }

    override suspend fun selectEpisodeAt(index: Int) {
        val currentTVShow = tvShowDataSourceable.retrieveCurrentTVShow() ?: return
        currentTVShowEpisodeUrlsCallback?.let {
            it(currentTVShow.episodesFrom(index = index).map { it.url })
        }
    }

    // VideoRepository
    
    override fun subscribeForCurrentTVShowUrls(callback: (urls: List<String>) -> Unit) {
        currentTVShowEpisodeUrlsCallback = callback
    }
}