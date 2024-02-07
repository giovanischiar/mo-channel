package io.schiar.mochannel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import io.schiar.mochannel.model.datasource.TVShowDataSource
import io.schiar.mochannel.model.datasource.api.TVShowsAPI
import io.schiar.mochannel.model.repository.MainRepository
import io.schiar.mochannel.view.screen.AppScreen
import io.schiar.mochannel.viewmodel.TVShowViewModel
import io.schiar.mochannel.viewmodel.TVShowsViewModel
import io.schiar.mochannel.viewmodel.VideoViewModel
import io.schiar.mochannel.viewmodel.util.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofitAPIHelper = RetrofitAPIHelper.getAPI()
        val tvShowLocalHostDataSource = TVShowDataSource(
            tvShowsAPI = retrofitAPIHelper.create(TVShowsAPI::class.java)
        )
        val mainRepository = MainRepository(tvShowDataSourceable = tvShowLocalHostDataSource)
        val viewModelFactory = ViewModelFactory(repository = mainRepository)
        val viewModelProvider = ViewModelProvider(owner = this, factory = viewModelFactory)
        setContent {
            AppScreen(
                tvShowsViewModel = viewModelProvider[TVShowsViewModel::class.java],
                tvShowViewModel = viewModelProvider[TVShowViewModel::class.java],
                videoViewModel =  viewModelProvider[VideoViewModel::class.java]
            )
        }
    }
}