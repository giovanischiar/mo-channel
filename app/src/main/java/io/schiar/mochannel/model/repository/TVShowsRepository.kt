package io.schiar.mochannel.model.repository

import io.schiar.mochannel.model.TVShow

interface TVShowsRepository {
    fun subscribeForTVShows(callback: (tvShows: List<TVShow>) -> Unit)
    suspend fun loadTVShows()
    suspend fun selectTVShowAt(index: Int)
}