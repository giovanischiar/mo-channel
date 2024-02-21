package io.schiar.mochannel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import io.schiar.mochannel.library.local.PreviewLocalData
import io.schiar.mochannel.library.retrofit.RetrofitAPIHelper
import io.schiar.mochannel.library.retrofit.TVShowsRetrofitAPI
import io.schiar.mochannel.library.retrofit.TVShowsRetrofitDataSource
import io.schiar.mochannel.library.room.MoChannelDatabase
import io.schiar.mochannel.library.room.ServerURLRoomDataSource
import io.schiar.mochannel.model.datasource.ServerURLDataSource
import io.schiar.mochannel.model.datasource.TVShowsDataSource
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
import io.schiar.mochannel.viewmodel.util.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val moChannelDatabase = MoChannelDatabase.getDatabase(context = applicationContext)
        val retrofitAPI = RetrofitAPIHelper.getAPI()
        val serverURLRoomDataSource = ServerURLRoomDataSource(
            serverURLRoomDAO = moChannelDatabase.serverURLRoomDAO()
        )
        val tvShowsRetrofitDataSource = TVShowsRetrofitDataSource(
            tvShowsRetrofitAPI = retrofitAPI.create(TVShowsRetrofitAPI::class.java),
            serverURLDataSource = serverURLRoomDataSource
        )
        val (
            settingsRepository, tvShowsRepository, tvShowRepository, videoRepository
        ) = createRepositories(
            serverURLDataSource = serverURLRoomDataSource,
            tvShowsDataSource = tvShowsRetrofitDataSource
        )
        val viewModelProvider = ViewModelProvider(
            owner = this,
            factory = ViewModelFactory(
                settingsRepository = settingsRepository,
                tvShowsRepository = tvShowsRepository,
                tvShowRepository = tvShowRepository,
                videoRepository = videoRepository
            )
        )
        setContent {
            AppScreen(
                settingsViewModel = viewModelProvider[SettingsViewModel::class.java],
                tvShowsViewModel = viewModelProvider[TVShowsViewModel::class.java],
                tvShowViewModel = viewModelProvider[TVShowViewModel::class.java],
                videoViewModel =  viewModelProvider[VideoViewModel::class.java]
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
        val videoRepository = VideoRepository()
        val tvShowRepository = TVShowRepository(currentEpisodeURLsListener = videoRepository)
        val tvShowsRepository = TVShowsRepository(
            tvShowsDataSource = tvShowsDataSource,
            currentTVShowChangedListener = tvShowRepository
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