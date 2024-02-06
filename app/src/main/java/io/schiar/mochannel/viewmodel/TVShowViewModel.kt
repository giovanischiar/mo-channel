package io.schiar.mochannel.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.schiar.mochannel.model.Episode
import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.repository.TVShowRepository
import io.schiar.mochannel.model.repository.MainRepository
import io.schiar.mochannel.view.viewdata.EpisodeViewData
import io.schiar.mochannel.view.viewdata.TVShowViewData
import io.schiar.mochannel.viewmodel.util.toViewData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TVShowViewModel(
    private val repository: TVShowRepository = MainRepository()
) : ViewModel() {
    private val _currentTVShow = MutableStateFlow<TVShowViewData?>(value = null)
    val currentTVShow: StateFlow<TVShowViewData?> = _currentTVShow

    private fun onCurrentTVShowChanged(tvShow: TVShow) {
        _currentTVShow.update { tvShow.toViewData() }
    }

    init { repository.subscribeForCurrentTVShow(::onCurrentTVShowChanged) }

    fun selectEpisodeAt(index: Int) {
        viewModelScope.launch { repository.selectEpisodeAt(index = index) }
    }
}