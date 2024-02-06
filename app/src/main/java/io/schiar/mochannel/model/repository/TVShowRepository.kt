package io.schiar.mochannel.model.repository

import io.schiar.mochannel.model.TVShow

interface TVShowRepository {
    fun subscribeForCurrentTVShow(callback: (tvShow: TVShow) -> Unit)
    suspend fun selectEpisodeAt(index: Int)
}