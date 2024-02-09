package io.schiar.mochannel.viewmodel.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.schiar.mochannel.model.repository.SettingsRepository
import io.schiar.mochannel.model.repository.TVShowRepository
import io.schiar.mochannel.model.repository.TVShowsRepository
import io.schiar.mochannel.model.repository.VideoRepository
import io.schiar.mochannel.viewmodel.SettingsViewModel
import io.schiar.mochannel.viewmodel.TVShowViewModel
import io.schiar.mochannel.viewmodel.TVShowsViewModel
import io.schiar.mochannel.viewmodel.VideoViewModel

class ViewModelFactory(
    private val settingsRepository: SettingsRepository,
    private val tvShowsRepository: TVShowsRepository,
    private val tvShowRepository: TVShowRepository,
    private val videoRepository: VideoRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when (modelClass) {
            SettingsViewModel::class.java -> SettingsViewModel(settingsRepository = settingsRepository)
            TVShowsViewModel::class.java  -> TVShowsViewModel(tvShowsRepository = tvShowsRepository)
            TVShowViewModel::class.java   -> TVShowViewModel(tvShowRepository = tvShowRepository)
            VideoViewModel::class.java    -> VideoViewModel(videoRepository = videoRepository)
            else -> throw IllegalArgumentException("Unknown view model class: ${modelClass.name}")
        } as T
    }
}
