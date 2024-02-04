package io.schiar.mochannel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import io.schiar.mochannel.model.repository.MainRepository
import io.schiar.mochannel.view.screen.AppScreen
import io.schiar.mochannel.viewmodel.TVShowViewModel
import io.schiar.mochannel.viewmodel.TVShowsViewModel
import io.schiar.mochannel.viewmodel.VideoViewModel
import io.schiar.mochannel.viewmodel.util.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModelFactory = ViewModelFactory(repository = MainRepository())
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