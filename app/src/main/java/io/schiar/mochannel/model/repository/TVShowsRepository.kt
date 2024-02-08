package io.schiar.mochannel.model.repository

import io.schiar.mochannel.model.ServerURL
import io.schiar.mochannel.model.TVShow

interface TVShowsRepository {
    fun subscribeForServerURLs(callback: ((ServerURL) -> Unit))
    fun subscribeForTVShows(callback: (tvShows: List<TVShow>) -> Unit)
    suspend fun loadTVShows()
    suspend fun loadServerURL()
    suspend fun selectTVShowAt(index: Int)
}