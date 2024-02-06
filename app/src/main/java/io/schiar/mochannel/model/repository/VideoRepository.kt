package io.schiar.mochannel.model.repository

interface VideoRepository {
    fun subscribeForCurrentTVShowUrls(callback: (urls: List<String>) -> Unit)
}