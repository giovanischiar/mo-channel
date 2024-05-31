package io.schiar.mochannel.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.schiar.mochannel.model.repository.TVShowsRepository
import io.schiar.mochannel.view.tvshows.TVShowsUiState
import io.schiar.mochannel.viewmodel.util.toTVShowsViewDataList
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class TVShowsViewModel @Inject constructor(
    private val tvShowsRepository: TVShowsRepository
) : ViewModel() {
    val tvShowsUiStateFlow by lazy {
        tvShowsRepository.tvShows
            .map { TVShowsUiState.TVShowsLoaded(it.toTVShowsViewDataList()) as TVShowsUiState }
            .catch { emit(TVShowsUiState.Error(it.message ?: "Unknown Error")) }
    }

    fun selectTVShowAt(index: Int) { tvShowsRepository.selectTVShowAt(index = index) }
}