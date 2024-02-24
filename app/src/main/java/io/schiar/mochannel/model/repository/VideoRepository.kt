package io.schiar.mochannel.model.repository

import io.schiar.mochannel.model.repository.listeners.CurrentEpisodeURLsListener
import javax.inject.Inject

class VideoRepository @Inject constructor(): CurrentEpisodeURLsListener {
    private var currentTVShowEpisodeUrlsCallback: ((List<String>) -> Unit)? = null

    fun subscribeForCurrentTVShowUrls(callback: (urls: List<String>) -> Unit) {
        currentTVShowEpisodeUrlsCallback = callback
    }

    override fun currentEpisodeURLsChangedTo(newEpisodeURLs: List<String>) {
        (currentTVShowEpisodeUrlsCallback ?: {})(newEpisodeURLs)
    }
}