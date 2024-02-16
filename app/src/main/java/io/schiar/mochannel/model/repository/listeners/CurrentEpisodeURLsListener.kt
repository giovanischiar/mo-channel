package io.schiar.mochannel.model.repository.listeners

interface CurrentEpisodeURLsListener {
    fun currentEpisodeURLsChangedTo(newEpisodeURLs: List<String>)
}