package io.schiar.mochannel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import io.schiar.mochannel.model.datasource.settings.SettingsDataSource
import io.schiar.mochannel.model.datasource.tvshow.TVShowDataSource
import io.schiar.mochannel.model.datasource.tvshow.api.API
import io.schiar.mochannel.model.datasource.tvshow.api.TVShowsServerAPI
import io.schiar.mochannel.model.repository.MainRepository
import io.schiar.mochannel.model.repository.SettingsRepository
import io.schiar.mochannel.view.screen.AppScreen
import io.schiar.mochannel.viewmodel.AppViewModel
import io.schiar.mochannel.viewmodel.SettingsViewModel
import io.schiar.mochannel.viewmodel.TVShowViewModel
import io.schiar.mochannel.viewmodel.TVShowsViewModel
import io.schiar.mochannel.viewmodel.VideoViewModel
import io.schiar.mochannel.viewmodel.util.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofitAPIHelper = RetrofitAPIHelper.getAPI()
        val api = retrofitAPIHelper.create(API::class.java)

        val settingsDataSource = SettingsDataSource()
        val tvShowDataSource = TVShowDataSource(
            tvShowsAPI = TVShowsServerAPI(api = api),
            serverURLRetriever = settingsDataSource
        )
        val settingsRepository = SettingsRepository(settingsDataSourceable = settingsDataSource)
        val mainRepository = MainRepository(tvShowDataSourceable = tvShowDataSource)
        val viewModelFactory = ViewModelFactory(
            mainRepository = mainRepository,
            settingsRepository = settingsRepository
        )
        val viewModelProvider = ViewModelProvider(owner = this, factory = viewModelFactory)
        setContent {
            AppScreen(
                appViewModel = viewModelProvider[AppViewModel::class.java],
                settingsViewModel = viewModelProvider[SettingsViewModel::class.java],
                tvShowsViewModel = viewModelProvider[TVShowsViewModel::class.java],
                tvShowViewModel = viewModelProvider[TVShowViewModel::class.java],
                videoViewModel =  viewModelProvider[VideoViewModel::class.java]
            )
        }
    }
}