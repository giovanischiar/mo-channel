package io.schiar.mochannel.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.schiar.mochannel.model.repository.TVShowRepository
import io.schiar.mochannel.view.uistate.CurrentEpisodesFromSeasonUiState
import io.schiar.mochannel.view.uistate.CurrentTVShowUiState
import io.schiar.mochannel.viewmodel.util.toViewData
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class TVShowViewModel @Inject constructor(
    private val tvShowRepository: TVShowRepository
) : ViewModel() {
    val currentTVShowUiStateFlow = tvShowRepository.currentTVShow
        .map { CurrentTVShowUiState.CurrentTVShowLoaded(it.toViewData()) }

    val currentEpisodesFromSeasonUiStateFlow = tvShowRepository.currentEpisodesFromSeason
        .map { CurrentEpisodesFromSeasonUiState.CurrentEpisodesFromSeasonLoaded(it) }

    fun selectEpisodesFromSeasonAt(index: Int) {
        tvShowRepository.selectEpisodesFromSeasonAt(index = index)
    }

    fun selectEpisodeOnIndexFromSeasonAt(index: Int, episodeIndex: Int) {
        tvShowRepository.urlsOfEpisodesFromIndexOfSeasonAt(
            index = index, episodeIndex = episodeIndex
        )
    }
}