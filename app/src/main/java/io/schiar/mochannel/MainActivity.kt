package io.schiar.mochannel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import io.schiar.mochannel.library.retrofit.RetrofitAPIHelper
import io.schiar.mochannel.library.retrofit.TVShowsServerAPI
import io.schiar.mochannel.library.room.MoChannelDatabase
import io.schiar.mochannel.model.Episode
import io.schiar.mochannel.model.ServerURL
import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.settings.SettingsDataSource
import io.schiar.mochannel.model.datasource.settings.requester.ServerURLDatabaseRequester
import io.schiar.mochannel.model.datasource.settings.requester.ServerURLMemoryRequester
import io.schiar.mochannel.model.datasource.settings.requester.ServerURLRequester
import io.schiar.mochannel.model.datasource.tvshow.TVShowDataSource
import io.schiar.mochannel.model.datasource.tvshow.requester.TVShowsLocalRequester
import io.schiar.mochannel.model.datasource.tvshow.requester.TVShowsServerRequester
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
    private fun createViewModelFactory(
        serverURLRequester: ServerURLRequester, tvShowsServerAPI: TVShowsServerAPI
    ): ViewModelFactory {
        val settingsDataSource = SettingsDataSource(serverURLRequester = serverURLRequester)
        val tvShowDataSource = TVShowDataSource(
            tvShowsRequester = TVShowsServerRequester(
                tvShowsServerAPI = tvShowsServerAPI, serverURLRequester = serverURLRequester
            ),
        )
        val videoRepository = VideoRepository()
        val tvShowRepository = TVShowRepository(
            tvShowDataSourceable = tvShowDataSource,
            currentEpisodeURLsListener = videoRepository
        )
        val tvShowsRepository = TVShowsRepository(
            tvShowDataSourceable = tvShowDataSource,
            currentTVShowChangedListener = tvShowRepository
        )

        val settingsRepository = SettingsRepository(
            settingsDataSourceable = settingsDataSource,
            serverURLChangedListener = tvShowsRepository
        )
        return ViewModelFactory(
            settingsRepository = settingsRepository,
            tvShowsRepository = tvShowsRepository,
            tvShowRepository = tvShowRepository,
            videoRepository = videoRepository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val moChannelDatabase = MoChannelDatabase.getDatabase(context = applicationContext)
        val serverURLEntityRequester = moChannelDatabase.serverURLRequester()
        val api = RetrofitAPIHelper.getAPI().create(TVShowsServerAPI::class.java)
        val viewModelFactory = createViewModelFactory(
            serverURLRequester = ServerURLDatabaseRequester(
                serverURLEntityRequester = serverURLEntityRequester
            ),
            tvShowsServerAPI = api
        )
        val viewModelProvider = ViewModelProvider(owner = this, factory = viewModelFactory)
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
        val serverURL = ServerURL(
            prefix = "https", url = "www.my-concerts-drive.com", port = "8080"
        )

        val tvShows = listOf(
            TVShow(
                name = "U2",
                episodes = listOf()
            ),

            TVShow(
                name = "Doja Cat",
                episodes = listOf()
            ),

            TVShow(
                name = "Jack Harlow",
                episodes = listOf()
            ),

            TVShow(
                name = "Twenty one Pilots",
                episodes = listOf()
            ),

            TVShow(
                name = "Taylor Swift",
                episodes = listOf(
                    Episode(name = "Reputation Tour 2018 Santa Clara/...Ready for It? Concert Beginning", url = ""),
                    Episode(name = "Reputation Tour 2018 Santa Clara/Style, Love Story, You Belong With Me, Performance snippets", url = ""),
                    Episode(name = "Reputation Tour 2018 Santa Clara/Singing Along with Delicate", url = ""),
                    Episode(name = "Reputation Tour 2018 Santa Clara/End Game Chorus Performance", url = ""),
                    Episode(name = "Reputation Tour 2018 Santa Clara/New Year's Day (encore)", url = ""),
                    Episode(name = "The Eras Tour 2024 Santa Clara/New Year's Day (encore)", url = "")
                )
            ),

            TVShow(
                name = "Bad Bunny",
                episodes = listOf()
            ),

            TVShow(
                name = "Metallica",
                episodes = listOf()
            ),

            TVShow(
                name = "Maroon 5",
                episodes = listOf()
            ),

            TVShow(
                name = "Panic! at The Disco",
                episodes = listOf(
                    Episode(
                        name = "Viva Las Vengeance Tour 2023 San Francisco/Say Amen (first song)",
                        url = ""
                    ),
                    Episode(
                        name = "Viva Las Vengeance Tour 2023 San Francisco/Viva Las Vengeance",
                        url = ""
                    ),
                    Episode(
                        name = "Pray for the Wicked Tour 2018 San Jose/Nine in the Afternoon",
                        url = ""
                    )
                )
            ),

            TVShow(
                name = "Mika",
                episodes = listOf()
            ),

            TVShow(
                name = "Aqua",
                episodes = listOf()
            ),
        )
        val settingsDataSource = SettingsDataSource(
            serverURLRequester = ServerURLMemoryRequester(serverURL = serverURL)
        )
        val tvShowDataSource = TVShowDataSource(
            tvShowsRequester = TVShowsLocalRequester(tvShows = tvShows)
        )
        val videoRepository = VideoRepository()
        val tvShowRepository = TVShowRepository(
            tvShowDataSourceable = tvShowDataSource,
            currentEpisodeURLsListener = videoRepository
        )
        val tvShowsRepository = TVShowsRepository(
            tvShowDataSourceable = tvShowDataSource,
            currentTVShowChangedListener = tvShowRepository
        )

        val settingsRepository = SettingsRepository(
            settingsDataSourceable = settingsDataSource,
            serverURLChangedListener = tvShowsRepository
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
        val settingsDataSource = SettingsDataSource()
        val tvShowDataSource = TVShowDataSource()
        val videoRepository = VideoRepository()
        val tvShowRepository = TVShowRepository(
            tvShowDataSourceable = tvShowDataSource,
            currentEpisodeURLsListener = videoRepository
        )
        val tvShowsRepository = TVShowsRepository(
            tvShowDataSourceable = tvShowDataSource,
            currentTVShowChangedListener = tvShowRepository
        )

        val settingsRepository = SettingsRepository(
            settingsDataSourceable = settingsDataSource,
            serverURLChangedListener = tvShowsRepository
        )
        AppScreen(
            settingsViewModel = SettingsViewModel(settingsRepository = settingsRepository),
            tvShowsViewModel = TVShowsViewModel(tvShowsRepository = tvShowsRepository),
            tvShowViewModel = TVShowViewModel(tvShowRepository = tvShowRepository),
            videoViewModel =  VideoViewModel(videoRepository = videoRepository)
        )
    }
}