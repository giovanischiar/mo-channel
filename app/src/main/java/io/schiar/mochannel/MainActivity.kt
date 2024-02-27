package io.schiar.mochannel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint
import io.schiar.mochannel.library.local.PreviewLocalData
import io.schiar.mochannel.model.datasource.ServerURLDataSource
import io.schiar.mochannel.model.datasource.TVShowsDataSource
import io.schiar.mochannel.model.datasource.local.CurrentEpisodeURLsLocalDataSource
import io.schiar.mochannel.model.datasource.local.CurrentTVShowLocalDataSource
import io.schiar.mochannel.model.datasource.local.ServerURLLocalDataSource
import io.schiar.mochannel.model.datasource.local.TVShowsLocalDataSource
import io.schiar.mochannel.model.repository.SettingsRepository
import io.schiar.mochannel.model.repository.TVShowRepository
import io.schiar.mochannel.model.repository.TVShowsRepository
import io.schiar.mochannel.model.repository.VideoRepository
import io.schiar.mochannel.view.screen.AppScreen
import io.schiar.mochannel.viewmodel.SettingsViewModel
import io.schiar.mochannel.viewmodel.TVShowViewModel
import io.schiar.mochannel.viewmodel.TVShowsViewModel
import io.schiar.mochannel.viewmodel.VideoViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val settingsViewModel: SettingsViewModel by viewModels()
    private val tvShowsViewModel: TVShowsViewModel by viewModels()
    private val tvShowViewModel: TVShowViewModel by viewModels()
    private val videoViewModel: VideoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppScreen(
                settingsViewModel = settingsViewModel,
                tvShowsViewModel = tvShowsViewModel,
                tvShowViewModel = tvShowViewModel,
                videoViewModel = videoViewModel
            )
        }
    }

    @Preview
    @Composable
    fun MainActivityTVShowsPreview() {
        val previewLocalData = PreviewLocalData()
        val serverURLLocalService = ServerURLLocalDataSource(serverURL = previewLocalData.serverURL)
        val tvShowsLocalService = TVShowsLocalDataSource(tvShows = previewLocalData.tvShows)
        val (
            settingsRepository, tvShowsRepository, tvShowRepository, videoRepository
        ) = createRepositories(
            serverURLDataSource = serverURLLocalService, tvShowsDataSource = tvShowsLocalService
        )
        AppScreen(
            settingsViewModel = SettingsViewModel(settingsRepository = settingsRepository),
            tvShowsViewModel = TVShowsViewModel(tvShowsRepository = tvShowsRepository),
            tvShowViewModel = TVShowViewModel(tvShowRepository = tvShowRepository),
            videoViewModel =  VideoViewModel(videoRepository = videoRepository)
        )
    }

    @Preview
    @Composable
    fun MainActivityPreview() {
        val (
            settingsRepository, tvShowsRepository, tvShowRepository, videoRepository
        ) = createRepositories(
            serverURLDataSource = ServerURLLocalDataSource(), tvShowsDataSource = TVShowsLocalDataSource()
        )
        AppScreen(
            settingsViewModel = SettingsViewModel(settingsRepository = settingsRepository),
            tvShowsViewModel = TVShowsViewModel(tvShowsRepository = tvShowsRepository),
            tvShowViewModel = TVShowViewModel(tvShowRepository = tvShowRepository),
            videoViewModel =  VideoViewModel(videoRepository = videoRepository)
        )
    }

    inner class Repositories(
        val settingsRepository: SettingsRepository,
        val tvShowsRepository: TVShowsRepository,
        val tvShowRepository: TVShowRepository,
        val videoRepository: VideoRepository
    )

    private fun createRepositories(
        serverURLDataSource: ServerURLDataSource, tvShowsDataSource: TVShowsDataSource
    ): Repositories {
        val currentTVShowDataSource = CurrentTVShowLocalDataSource()
        val currentEpisodeURLsDataSource = CurrentEpisodeURLsLocalDataSource()
        val videoRepository = VideoRepository(
            currentEpisodeURLsDataSource = currentEpisodeURLsDataSource
        )
        val tvShowRepository = TVShowRepository(
            currentTVShowDataSource = currentTVShowDataSource,
            currentEpisodeURLsDataSource = currentEpisodeURLsDataSource
        )
        val tvShowsRepository = TVShowsRepository(
            tvShowsDataSource = tvShowsDataSource,
            currentTVShowDataSource = currentTVShowDataSource
        )
        val settingsRepository = SettingsRepository(serverURLDataSource = serverURLDataSource)
        return Repositories(
            settingsRepository = settingsRepository,
            tvShowsRepository = tvShowsRepository,
            tvShowRepository = tvShowRepository,
            videoRepository = videoRepository
        )
    }

    operator fun Repositories.component1(): SettingsRepository { return settingsRepository }
    operator fun Repositories.component2(): TVShowsRepository { return tvShowsRepository }
    operator fun Repositories.component3(): TVShowRepository { return tvShowRepository }
    operator fun Repositories.component4(): VideoRepository { return videoRepository }
}