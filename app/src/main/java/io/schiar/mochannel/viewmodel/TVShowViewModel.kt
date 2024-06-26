package io.schiar.mochannel.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.schiar.mochannel.model.repository.TVShowRepository
import io.schiar.mochannel.view.tvshow.uistate.CurrentEpisodesFromSeasonUiState
import io.schiar.mochannel.view.tvshow.uistate.CurrentTVShowUiState
import io.schiar.mochannel.viewmodel.util.toEpisodeViewDataList
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
        .map { episodes ->
            CurrentEpisodesFromSeasonUiState.CurrentEpisodesFromSeasonLoaded(
                episodes.toEpisodeViewDataList()
            )
        }

    fun selectEpisodesFromSeasonAt(index: Int) {
        tvShowRepository.selectEpisodesFromSeasonAt(index = index)
    }

    fun selectEpisodeOnIndexFromSeasonAt(index: Int, episodeIndex: Int) {
        tvShowRepository.urlsOfEpisodesFromIndexOfSeasonAt(
            index = index, episodeIndex = episodeIndex
        )
    }
}