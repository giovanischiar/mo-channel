package io.schiar.mochannel.view.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.schiar.mochannel.view.components.ListView
import io.schiar.mochannel.view.uistate.CurrentEpisodesFromSeasonUiState
import io.schiar.mochannel.view.uistate.CurrentTVShowUiState

@Composable
fun TVShowScreen(
    currentTVShowUiState: CurrentTVShowUiState,
    currentEpisodesFromSeasonUiState: CurrentEpisodesFromSeasonUiState,
    selectEpisodesFromSeasonAt: (index: Int) -> Unit,
    selectEpisodeOnIndexFromSeasonAt: (index: Int, episodeIndex: Int) -> Unit,
    onVideoPressed: () -> Unit = {}
) {
    val currentTVShow = when (currentTVShowUiState) {
        is CurrentTVShowUiState.Loading -> null
        is CurrentTVShowUiState.CurrentTVShowLoaded -> currentTVShowUiState.tvShow
    }

    val currentEpisodesFromSeason = when(currentEpisodesFromSeasonUiState) {
        is CurrentEpisodesFromSeasonUiState.Loading -> emptyList()
        is CurrentEpisodesFromSeasonUiState.CurrentEpisodesFromSeasonLoaded -> {
            currentEpisodesFromSeasonUiState.episodes
        }
    }

    var currentSeasonIndex by remember { mutableIntStateOf(value = 0) }
    val seasonTitles = (currentTVShow ?: return).seasonTitles
    LaunchedEffect(Unit) { selectEpisodesFromSeasonAt(currentSeasonIndex) }
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
                        selectEpisodesFromSeasonAt(index)
                        currentSeasonIndex = index
                    },
                    onButtonPressedAt = { index ->
                        selectEpisodesFromSeasonAt(index)
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
                onButtonPressedAt = { episodeIndex ->
                    selectEpisodeOnIndexFromSeasonAt(currentSeasonIndex, episodeIndex)
                    onVideoPressed()
                }
            )
        }
    }
}