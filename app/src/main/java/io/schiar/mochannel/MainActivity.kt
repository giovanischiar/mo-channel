package io.schiar.mochannel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
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
import io.schiar.mochannel.view.settings.settingsScreen
import io.schiar.mochannel.view.tvshow.tvShowScreen
import io.schiar.mochannel.view.tvshows.tvShowsScreen
import io.schiar.mochannel.view.video.videoScreen
import io.schiar.mochannel.viewmodel.SettingsViewModel
import io.schiar.mochannel.viewmodel.TVShowViewModel
import io.schiar.mochannel.viewmodel.TVShowsViewModel
import io.schiar.mochannel.viewmodel.VideoViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { Navigation() }
    }

    @Composable
    private fun Navigation(
        tvShowsViewModel: TVShowsViewModel? = null,
        settingsViewModel: SettingsViewModel? = null,
        tvShowViewModel: TVShowViewModel? = null,
        videoViewModel: VideoViewModel? = null
    ) {
        val navController = rememberNavController()

        Box {
            NavHost(
                modifier = Modifier.background(color = colorResource(R.color.backgroundColor)),
                navController = navController,
                startDestination = "TVShows"
            ) {
                settingsScreen(settingsViewModel = settingsViewModel)
                
                tvShowsScreen(
                    tvShowsViewModel = tvShowsViewModel,
                    onNavigateToSettings = { navController.navigate(route = "Settings") },
                    onNavigateToTVShow = { navController.navigate(route = "TVShow") }
                )

                tvShowScreen(
                    tvShowViewModel = tvShowViewModel,
                    onNavigateToVideo = { navController.navigate(route = "Video") }
                )

                videoScreen(videoViewModel = videoViewModel)
            }
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
        Navigation(
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
        Navigation(
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
        return Repositories(
            settingsRepository = SettingsRepository(serverURLDataSource = serverURLDataSource),
            tvShowsRepository = TVShowsRepository(
                tvShowsDataSource = tvShowsDataSource,
                currentTVShowDataSource = currentTVShowDataSource
            ),
            tvShowRepository = TVShowRepository(
                currentTVShowDataSource = currentTVShowDataSource,
                currentEpisodeURLsDataSource = currentEpisodeURLsDataSource
            ),
            videoRepository = VideoRepository(
                currentEpisodeURLsDataSource = currentEpisodeURLsDataSource
            )
        )
    }

    operator fun Repositories.component1(): SettingsRepository { return settingsRepository }
    operator fun Repositories.component2(): TVShowsRepository { return tvShowsRepository }
    operator fun Repositories.component3(): TVShowRepository { return tvShowRepository }
    operator fun Repositories.component4(): VideoRepository { return videoRepository }
}