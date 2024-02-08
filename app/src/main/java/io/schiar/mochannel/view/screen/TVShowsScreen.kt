package io.schiar.mochannel.view.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.schiar.mochannel.view.components.ListView
import io.schiar.mochannel.viewmodel.TVShowsViewModel

@Composable
fun TVShowsScreen(
    serverURLChanged: Boolean,
    tvShowsViewModel: TVShowsViewModel,
    onTVShowPressed: () -> Unit = {}
) {
    LaunchedEffect(Unit) { if (serverURLChanged) { tvShowsViewModel.loadTVShows() } }
    val tvShows by tvShowsViewModel.tvShows.collectAsState()
    Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
        ListView(
            buttonTitles = tvShows.map { it.name },
            onButtonPressedAt = { index ->
                tvShowsViewModel.selectTVShowAt(index = index)
                onTVShowPressed()
            }
        )
    }
}