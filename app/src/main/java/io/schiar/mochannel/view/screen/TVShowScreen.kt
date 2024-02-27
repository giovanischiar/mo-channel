package io.schiar.mochannel.view.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.schiar.mochannel.view.components.ListView
import io.schiar.mochannel.viewmodel.TVShowViewModel

@Composable
fun TVShowScreen(tvShowViewModel: TVShowViewModel, onVideoPressed: () -> Unit = {}) {
    val currentTVShow by tvShowViewModel.currentTVShow.collectAsState(initial = null)
    val currentEpisodesFromSeason by tvShowViewModel.currentEpisodesFromSeason.collectAsState(
        initial = emptyList()
    )
    (currentTVShow ?: return).name
    var currentSeasonIndex by remember { mutableIntStateOf(value = 0) }
    val seasonTitles = (currentTVShow ?: return).seasonTitles
    LaunchedEffect(Unit) { tvShowViewModel.selectEpisodesFromSeasonAt(index = currentSeasonIndex) }
    Row {
        if (seasonTitles.size > 1) {
            Box(
                modifier = Modifier.weight(0.4f).fillMaxHeight(),
                contentAlignment = Alignment.Center
            ) {
                ListView(
                    modifier = Modifier.fillMaxWidth(),
                    buttonTitles = seasonTitles,
                    onButtonFocusedAt = { index ->
                        tvShowViewModel.selectEpisodesFromSeasonAt(index = index)
                        currentSeasonIndex = index
                    },
                    onButtonPressedAt = { index ->
                        tvShowViewModel.selectEpisodesFromSeasonAt(index = index)
                        currentSeasonIndex = index
                    }
                )
            }
        }

        Box(
            modifier = Modifier.weight(0.6f).fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            ListView(
                modifier = Modifier.fillMaxWidth(),
                buttonTitles = currentEpisodesFromSeason.map { it.name },
                onButtonPressedAt = { index ->
                    tvShowViewModel.selectEpisodeOnIndexFromSeasonAt(
                        index = currentSeasonIndex,
                        episodeIndex = index
                    )
                    onVideoPressed()
                }
            )
        }
    }
}