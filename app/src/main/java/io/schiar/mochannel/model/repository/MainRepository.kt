package io.schiar.mochannel.model.repository

import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.repository.tvshow.TVShowAPIRepository
import io.schiar.mochannel.model.repository.tvshow.TVShowRepository
import kotlinx.coroutines.coroutineScope

class MainRepository(
    private val tvShowRepository: TVShowRepository = TVShowAPIRepository()
): TVShowsRepository {
    private val tvShows = mutableListOf<TVShow>()

    override suspend fun loadTvShows(onTVShowsLoaded: (tvShows: List<TVShow>) -> Unit) = coroutineScope {
        tvShowRepository.loadTVShows {
            onTVShowsLoaded(it)
        }
    }
}