package io.schiar.mochannel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.repository.MainRepository
import io.schiar.mochannel.model.repository.TVShowsRepository
import io.schiar.mochannel.view.viewdata.TVShowViewData
import io.schiar.mochannel.viewmodel.util.toViewData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TVShowsViewModel(
    private val repository: TVShowsRepository = MainRepository()
) : ViewModel() {
    private val _tvShows = MutableStateFlow<List<TVShowViewData>>(emptyList())
    val tvShows: StateFlow<List<TVShowViewData>> = _tvShows

    private fun onTVShowsChanged(tvShows: List<TVShow>) {
        _tvShows.update { tvShows.map { tvShow -> tvShow.toViewData() } }
    }

    init {
        repository.subscribeForTVShows(::onTVShowsChanged)
        viewModelScope.launch { repository.loadTVShows() }
    }

    fun selectTVShowAt(index: Int) {
        viewModelScope.launch { repository.selectTVShowAt(index = index) }
    }
}