package io.schiar.mochannel.viewmodel

import androidx.lifecycle.ViewModel
import io.schiar.mochannel.model.repository.MainRepository
import io.schiar.mochannel.model.repository.TVShowsRepository
import io.schiar.mochannel.view.viewdata.TVShowViewData
import io.schiar.mochannel.viewmodel.util.toViewData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class TVShowsViewModel(
    private val repository: TVShowsRepository = MainRepository()
) : ViewModel() {
    private val _tvShows = MutableStateFlow<List<TVShowViewData>>(emptyList())
    val tvShows: StateFlow<List<TVShowViewData>> = _tvShows

    suspend fun loadTVShows() {
        repository.loadTVShows { newTVShows ->
            _tvShows.update { newTVShows.map { tvShow -> tvShow.toViewData() } }
        }
    }

    fun selectTVShow(name: String) {
        repository.selectTVShow(name = name)
    }
}