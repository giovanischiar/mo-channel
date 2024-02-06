package io.schiar.mochannel.model.repository

import io.schiar.mochannel.model.Episode
import io.schiar.mochannel.model.TVShow

interface TVShowRepository {
    fun subscribeForCurrentTVShow(callback: (tvShow: TVShow) -> Unit)
    fun subscribeForCurrentEpisodesFromSeason(callback: (episodes: List<Episode>) -> Unit)
    suspend fun selectEpisodesFromSeasonAt(index: Int)
    suspend fun urlsOfEpisodesFromIndexOfSeasonAt(index: Int, episodeIndex: Int)
}