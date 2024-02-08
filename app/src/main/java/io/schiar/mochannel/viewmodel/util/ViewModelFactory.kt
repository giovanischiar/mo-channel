package io.schiar.mochannel.viewmodel.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.schiar.mochannel.model.repository.MainRepository
import io.schiar.mochannel.viewmodel.SettingsViewModel
import io.schiar.mochannel.viewmodel.TVShowViewModel
import io.schiar.mochannel.viewmodel.TVShowsViewModel
import io.schiar.mochannel.viewmodel.VideoViewModel

class ViewModelFactory(private val mainRepository: MainRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            SettingsViewModel::class.java -> SettingsViewModel(repository = mainRepository)
            TVShowsViewModel::class.java  -> TVShowsViewModel(repository = mainRepository)
            TVShowViewModel::class.java   -> TVShowViewModel(repository = mainRepository)
            VideoViewModel::class.java    -> VideoViewModel(repository = mainRepository)
            else -> throw IllegalArgumentException("Unknown view model class: ${modelClass.name}")
        } as T
    }
}
