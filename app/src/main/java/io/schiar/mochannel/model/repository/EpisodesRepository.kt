package io.schiar.mochannel.model.repository

import io.schiar.mochannel.model.Episode

interface EpisodesRepository {
    var currentTVShow: String
    fun loadEpisodes(): List<Episode>
    fun selectEpisode(url: String)
}