package io.schiar.mochannel.view.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import io.schiar.mochannel.viewmodel.TVShowViewModel

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TVShowScreen(tvShowViewModel: TVShowViewModel, onPressVideo: () -> Unit = {}) {
    val episodes by tvShowViewModel.episodes.collectAsState()
    LaunchedEffect(Unit) { tvShowViewModel.loadEpisodes() }
    Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(episodes.size) { index ->
                val episode = episodes[index]
                Button(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp, vertical = 5.dp),
                    onClick= {
                        tvShowViewModel.selectEpisode(url = episode.url)
                        onPressVideo()
                    }
                ) {
                    Text(episode.name)
                }
            }
        }
    }
}