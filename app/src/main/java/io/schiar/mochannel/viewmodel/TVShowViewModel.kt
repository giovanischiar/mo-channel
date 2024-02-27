package io.schiar.mochannel.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.schiar.mochannel.model.repository.TVShowRepository
import io.schiar.mochannel.viewmodel.util.toViewData
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class TVShowViewModel @Inject constructor(
    private val tvShowRepository: TVShowRepository
) : ViewModel() {
    val currentTVShow = tvShowRepository.currentTVShow.map { it.toViewData() }
    val currentEpisodesFromSeason = tvShowRepository.currentEpisodesFromSeason

    fun selectEpisodesFromSeasonAt(index: Int) {
        tvShowRepository.selectEpisodesFromSeasonAt(index = index)
    }

    fun selectEpisodeOnIndexFromSeasonAt(index: Int, episodeIndex: Int) {
        tvShowRepository.urlsOfEpisodesFromIndexOfSeasonAt(
            index = index, episodeIndex = episodeIndex
        )
    }
}