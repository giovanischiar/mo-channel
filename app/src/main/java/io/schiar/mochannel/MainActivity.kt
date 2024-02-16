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
import io.schiar.mochannel.library.retrofit.TVShowsRetrofitService
import io.schiar.mochannel.library.room.MoChannelDatabase
import io.schiar.mochannel.library.room.ServerURLRoomService
import io.schiar.mochannel.model.datasource.SettingsDataSource
import io.schiar.mochannel.model.datasource.TVShowsDataSource
import io.schiar.mochannel.model.datasource.service.ServerURLService
import io.schiar.mochannel.model.datasource.service.TVShowsService
import io.schiar.mochannel.model.datasource.service.local.ServerURLLocalService
import io.schiar.mochannel.model.datasource.service.local.TVShowsLocalService
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
        val serverURLRoomService = ServerURLRoomService(
            serverURLRoomDAO = moChannelDatabase.serverURLRoomDAO()
        )
        val tvShowsRetrofitService = TVShowsRetrofitService(
            tvShowsRetrofitAPI = retrofitAPI.create(TVShowsRetrofitAPI::class.java),
            serverURLService = serverURLRoomService
        )
        val (
            settingsRepository, tvShowsRepository, tvShowRepository, videoRepository
        ) = createRepositories(
            serverURLService = serverURLRoomService, tvShowsService = tvShowsRetrofitService
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
        val serverURLLocalService = ServerURLLocalService(serverURL = previewLocalData.serverURL)
        val tvShowsLocalService = TVShowsLocalService(tvShows = previewLocalData.tvShows)
        val (
            settingsRepository, tvShowsRepository, tvShowRepository, videoRepository
        ) = createRepositories(
            serverURLService = serverURLLocalService, tvShowsService = tvShowsLocalService
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
            serverURLService = ServerURLLocalService(), tvShowsService = TVShowsLocalService()
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
        serverURLService: ServerURLService, tvShowsService: TVShowsService
    ): Repositories {
        val settingsDataSource = SettingsDataSource(serverURLService = serverURLService)
        val tvShowDataSource = TVShowsDataSource(tvShowsService = tvShowsService)
        val videoRepository = VideoRepository()
        val tvShowRepository = TVShowRepository(
            tvShowsDataSource = tvShowDataSource,
            currentEpisodeURLsListener = videoRepository
        )
        val tvShowsRepository = TVShowsRepository(
            tvShowsDataSource = tvShowDataSource,
            currentTVShowChangedListener = tvShowRepository
        )
        val settingsRepository = SettingsRepository(
            settingsDataSource = settingsDataSource,
            serverURLChangedListener = tvShowsRepository
        )
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