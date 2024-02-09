package io.schiar.mochannel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import io.schiar.mochannel.model.datasource.settings.SettingsDataSource
import io.schiar.mochannel.model.datasource.settings.database.ServerURLEntity
import io.schiar.mochannel.model.datasource.settings.util.ServerURLLocalDAO
import io.schiar.mochannel.model.datasource.tvshow.TVShowDataSource
import io.schiar.mochannel.model.datasource.tvshow.api.API
import io.schiar.mochannel.model.datasource.tvshow.api.TVShowsServerAPI
import io.schiar.mochannel.model.datasource.tvshow.api.json.EpisodeJSON
import io.schiar.mochannel.model.datasource.tvshow.api.json.TVShowJSON
import io.schiar.mochannel.model.datasource.tvshow.util.TVShowsLocalAPI
import io.schiar.mochannel.model.repository.MainRepository
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
        val retrofitAPIHelper = RetrofitAPIHelper.getAPI()
        val api = retrofitAPIHelper.create(API::class.java)
        val settingsDataSource = SettingsDataSource(serverURLDAO = moChannelDatabase.serverURLDAO())
        val tvShowDataSource = TVShowDataSource(
            tvShowsAPI = TVShowsServerAPI(api = api),
            serverURLRetriever = settingsDataSource
        )
        val mainRepository = MainRepository(
            tvShowDataSourceable = tvShowDataSource,
            settingsDataSourceable = settingsDataSource
        )
        val viewModelFactory = ViewModelFactory(
            mainRepository = mainRepository
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
        val serverURLEntity = ServerURLEntity(
            prefix = "https", url = "www.my-concerts-drive.com", port = "8080"
        )

        val tvShowJSONs = listOf(
            TVShowJSON(
                name = "U2",
                episodes = listOf()
            ),

            TVShowJSON(
                name = "Doja Cat",
                episodes = listOf()
            ),

            TVShowJSON(
                name = "Jack Harlow",
                episodes = listOf()
            ),

            TVShowJSON(
                name = "Twenty one Pilots",
                episodes = listOf()
            ),

            TVShowJSON(
                name = "Taylor Swift",
                episodes = listOf(
                    EpisodeJSON(name = "Reputation Tour 2018 Santa Clara/...Ready for It? Concert Beginning", url = ""),
                    EpisodeJSON(name = "Reputation Tour 2018 Santa Clara/Style, Love Story, You Belong With Me, Performance snippets", url = ""),
                    EpisodeJSON(name = "Reputation Tour 2018 Santa Clara/Singing Along with Delicate", url = ""),
                    EpisodeJSON(name = "Reputation Tour 2018 Santa Clara/End Game Chorus Performance", url = ""),
                    EpisodeJSON(name = "Reputation Tour 2018 Santa Clara/New Year's Day (encore)", url = ""),
                    EpisodeJSON(name = "The Eras Tour 2024 Santa Clara/New Year's Day (encore)", url = "")
                )
            ),

            TVShowJSON(
                name = "Bad Bunny",
                episodes = listOf()
            ),

            TVShowJSON(
                name = "Metallica",
                episodes = listOf()
            ),

            TVShowJSON(
                name = "Maroon 5",
                episodes = listOf()
            ),

            TVShowJSON(
                name = "Panic! at The Disco",
                episodes = listOf(
                    EpisodeJSON(name = "Viva Las Vengeance Tour 2023 San Francisco/Say Amen (first song)", url = ""),
                    EpisodeJSON(name = "Viva Las Vengeance Tour 2023 San Francisco/Viva Las Vengeance", url = ""),
                    EpisodeJSON(name = "Pray for the Wicked Tour 2018 San Jose/Nine in the Afternoon", url = "")
                )
            ),

            TVShowJSON(
                name = "Mika",
                episodes = listOf()
            ),

            TVShowJSON(
                name = "Aqua",
                episodes = listOf()
            ),
        )
        val settingsDataSource = SettingsDataSource(
            serverURLDAO = ServerURLLocalDAO(serverURLEntity = serverURLEntity)
        )
        val tvShowDataSource = TVShowDataSource(
            tvShowsAPI = TVShowsLocalAPI(tvShowJSONs = tvShowJSONs),
            serverURLRetriever = settingsDataSource
        )
        val mainRepository = MainRepository(
            tvShowDataSourceable = tvShowDataSource,
            settingsDataSourceable = settingsDataSource
        )
        AppScreen(
            settingsViewModel = SettingsViewModel(repository = mainRepository),
            tvShowsViewModel = TVShowsViewModel(repository = mainRepository),
            tvShowViewModel = TVShowViewModel(repository = mainRepository),
            videoViewModel =  VideoViewModel(repository = mainRepository)
        )
    }

    @Preview
    @Composable
    fun MainActivityPreview() {
        val settingsDataSource = SettingsDataSource()
        val tvShowDataSource = TVShowDataSource(serverURLRetriever = settingsDataSource)
        val mainRepository = MainRepository(
            tvShowDataSourceable = tvShowDataSource,
            settingsDataSourceable = settingsDataSource
        )
        AppScreen(
            settingsViewModel = SettingsViewModel(repository = mainRepository),
            tvShowsViewModel = TVShowsViewModel(repository = mainRepository),
            tvShowViewModel = TVShowViewModel(repository = mainRepository),
            videoViewModel =  VideoViewModel(repository = mainRepository)
        )
    }
}