package io.schiar.mochannel.model.repository

import io.schiar.mochannel.model.Episode
import io.schiar.mochannel.model.ServerURL
import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.settings.SettingsDataSource
import io.schiar.mochannel.model.datasource.settings.SettingsDataSourceable
import io.schiar.mochannel.model.datasource.tvshow.TVShowDataSource
import io.schiar.mochannel.model.datasource.tvshow.TVShowDataSourceable

class MainRepository(
    private val tvShowDataSourceable: TVShowDataSourceable = TVShowDataSource(),
    private val settingsDataSourceable: SettingsDataSourceable = SettingsDataSource()
) : SettingsRepository, TVShowsRepository, TVShowRepository, VideoRepository {
    // TVShowsRepository
    private var tvShowsCallback: ((List<TVShow>) -> Unit)? = null

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
    private var currentTVShowCallback: ((TVShow) -> Unit)? = null
    private var currentEpisodesFromSeasonCallback: ((List<Episode>) -> Unit)? = null

    override fun subscribeForCurrentTVShow(callback: (tvShow: TVShow) -> Unit) {
        currentTVShowCallback = callback
    }

    override fun subscribeForCurrentEpisodesFromSeason(
        callback: (episodes: List<Episode>) -> Unit
    ) {
        currentEpisodesFromSeasonCallback = callback
    }

    override suspend fun selectEpisodesFromSeasonAt(index: Int) {
        val currentTVShow = tvShowDataSourceable.retrieveCurrentTVShow() ?: return
        currentEpisodesFromSeasonCallback?.let {
            it(currentTVShow.episodesFromSeasonAt(index = index))
        }
    }

    override suspend fun urlsOfEpisodesFromIndexOfSeasonAt(index: Int, episodeIndex: Int) {
        val currentTVShow = tvShowDataSourceable.retrieveCurrentTVShow() ?: return
        currentTVShowEpisodeUrlsCallback?.let {
            val urls = currentTVShow.urlsOfEpisodesFromIndexOfSeasonAt(
                index = index, episodeIndex = episodeIndex
            )
            it(urls)
        }
    }

    // VideoRepository
    private var currentTVShowEpisodeUrlsCallback: ((List<String>) -> Unit)? = null
    
    override fun subscribeForCurrentTVShowUrls(callback: (urls: List<String>) -> Unit) {
        currentTVShowEpisodeUrlsCallback = callback
    }

    // SettingsRepository
    private var serverURLsCallbacks: MutableList<((ServerURL) -> Unit)> = mutableListOf()

    private suspend fun serverURLRetrievedFromDataSource(): ServerURL {
        return settingsDataSourceable.retrieveServerURL()
    }

    private suspend fun updateServerURLTo(newServerURL: ServerURL) {
        settingsDataSourceable.updateServerURLTo(newServerURL = newServerURL)
        serverURLsCallbacks.invokeAll(serverURL = newServerURL)
    }

    override fun subscribeForServerURLs(callback: ((ServerURL) -> Unit)) {
        serverURLsCallbacks.add(callback)
    }

    override suspend fun updatePrefixTo(newPrefix: String) {
        val serverURL = serverURLRetrievedFromDataSource()
        updateServerURLTo(newServerURL = serverURL.prefixUpdatedTo(newPrefix = newPrefix))
    }

    override suspend fun updateURLTo(newURL: String) {
        val serverURL = serverURLRetrievedFromDataSource()
        updateServerURLTo(newServerURL = serverURL.urlUpdatedTo(newURL = newURL))
    }

    override suspend fun updatePortTo(newPort: String) {
        val serverURL = serverURLRetrievedFromDataSource()
        updateServerURLTo(newServerURL = serverURL.portUpdatedTo(newPort = newPort))
    }

    override suspend fun loadServerURL() {
        val serverURL = settingsDataSourceable.retrieveServerURL()
        serverURLsCallbacks.invokeAll(serverURL = serverURL)
    }
}