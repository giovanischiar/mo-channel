package io.schiar.mochannel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.schiar.mochannel.model.repository.MainRepository
import io.schiar.mochannel.model.repository.TVShowsRepository
import io.schiar.mochannel.view.viewdata.TVShowViewData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TVShowsViewModel(
    private val tvShowsRepository: TVShowsRepository = MainRepository()
) : ViewModel() {
    private val _tvShows = MutableStateFlow<List<TVShowViewData>>(emptyList())
    val tvShows: StateFlow<List<TVShowViewData>> = _tvShows

    suspend fun loadTVShows() {
        tvShowsRepository.loadTvShows { newTVShows ->
            _tvShows.update { newTVShows.map { tvShow -> tvShow.toViewData() } }
        }
    }
}