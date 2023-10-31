package io.schiar.mochannel.model.repository

import io.schiar.mochannel.model.Episode
import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.repository.tvshow.TVShowAPIRepository
import io.schiar.mochannel.model.repository.tvshow.TVShowRepository
import kotlinx.coroutines.coroutineScope

class MainRepository(
    private val tvShowRepository: TVShowRepository = TVShowAPIRepository()
) : TVShowsRepository, EpisodesRepository, EpisodeRepository {
    private val tvShowEpisodes = mutableMapOf<String, List<Episode>>()
    override var currentTVShow = ""
    override var currentEpisodeURL = ""

    override suspend fun loadTVShows(
        onTVShowsLoaded: (tvShows: List<TVShow>) -> Unit
    ) = coroutineScope {
        tvShowRepository.loadTVShows { tvShows ->
            tvShows.forEach { tvShow -> tvShowEpisodes[tvShow.name] = tvShow.episodes }
            onTVShowsLoaded(tvShows)
        }
    }

    override fun selectTVShow(name: String) {
        currentTVShow = name
    }

    override fun loadEpisodes(): List<Episode> {
        return tvShowEpisodes[currentTVShow] ?: emptyList()
    }

    override fun selectEpisode(url: String) {
        currentEpisodeURL = url
    }
}