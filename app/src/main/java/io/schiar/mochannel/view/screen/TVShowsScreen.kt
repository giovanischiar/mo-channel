package io.schiar.mochannel.view.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import io.schiar.mochannel.view.components.ListView
import io.schiar.mochannel.viewmodel.TVShowsViewModel

@Composable
fun TVShowsScreen(tvShowsViewModel: TVShowsViewModel, onTVShowPressed: () -> Unit = {}) {
    val tvShows by tvShowsViewModel.tvShows.collectAsState(initial = emptyList())
    val loading by tvShowsViewModel.loading.collectAsState()
    val errorResponse by tvShowsViewModel.errorMessage.collectAsState()
    if (errorResponse.isNotEmpty()) {
        Toast.makeText(LocalContext.current, errorResponse, Toast.LENGTH_SHORT).show()
        tvShowsViewModel.cleanErrorMessage()
    }
    Box(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .fillMaxHeight()
            .padding(top = 104.dp), contentAlignment = Alignment.Center) {
            ListView(
                buttonTitles = tvShows.map { it.name },
                onButtonPressedAt = { index ->
                    tvShowsViewModel.selectTVShowAt(index = index)
                    onTVShowPressed()
                }
            )
        }
        if (loading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
    }
}