package io.schiar.mochannel.model.repository

import io.schiar.mochannel.model.repository.listeners.CurrentEpisodeURLsListener

class VideoRepository: CurrentEpisodeURLsListener {
    private var currentTVShowEpisodeUrlsCallback: ((List<String>) -> Unit)? = null

    fun subscribeForCurrentTVShowUrls(callback: (urls: List<String>) -> Unit) {
        currentTVShowEpisodeUrlsCallback = callback
    }

    override fun onCurrentEpisodeURLsListener(urls: List<String>) {
        (currentTVShowEpisodeUrlsCallback ?: {})(urls)
    }
}