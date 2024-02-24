package io.schiar.mochannel.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.schiar.mochannel.model.repository.TVShowsRepository
import io.schiar.mochannel.viewmodel.util.toViewDataList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TVShowsViewModel @Inject constructor(
    private val tvShowsRepository: TVShowsRepository
) : ViewModel() {
    val tvShows = tvShowsRepository.tvShows
        .onStart { _loading.update { true } }
        .onEach { cleanLoading() }
        .map { it.toViewDataList() }
        .catch { catchException(it) }
    private val _loading = MutableStateFlow(value = false)
    val loading: StateFlow<Boolean> = _loading
    private val _errorMessage = MutableStateFlow(value = "")
    val errorMessage: StateFlow<String> = _errorMessage

    fun cleanErrorMessage() { _errorMessage.update { "" } }
    fun selectTVShowAt(index: Int) { tvShowsRepository.selectTVShowAt(index = index) }

    private fun cleanLoading() { _loading.update { false } }
    private fun catchException(throwable: Throwable) {
        cleanLoading()
        _errorMessage.update { throwable.message ?: "" }
    }
}