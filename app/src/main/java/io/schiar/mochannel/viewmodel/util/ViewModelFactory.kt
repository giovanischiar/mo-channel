package io.schiar.mochannel.viewmodel.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.schiar.mochannel.model.repository.MainRepository
import io.schiar.mochannel.viewmodel.TVShowViewModel
import io.schiar.mochannel.viewmodel.TVShowsViewModel
import io.schiar.mochannel.viewmodel.VideoViewModel

class ViewModelFactory(private val repository: MainRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            TVShowsViewModel::class.java -> TVShowsViewModel(repository = repository)
            TVShowViewModel::class.java -> TVShowViewModel(repository = repository)
            VideoViewModel::class.java -> VideoViewModel(repository = repository)
            else -> throw IllegalArgumentException("Unknown view model class: ${modelClass.name}")
        } as T
    }
}
