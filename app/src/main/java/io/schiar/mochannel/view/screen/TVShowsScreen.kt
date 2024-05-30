package io.schiar.mochannel.view.screen

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import io.schiar.mochannel.R
import io.schiar.mochannel.view.components.ListView
import io.schiar.mochannel.view.uistate.TVShowsUiState

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TVShowsScreen(
    tvShowsUiState: TVShowsUiState,
    selectTVShowAt: (index: Int) -> Unit,
    onSettingsPressed: () -> Unit = {},
    onTVShowPressed: () -> Unit = {}
) {
    val tvShows = when (tvShowsUiState) {
        is TVShowsUiState.TVShowsLoaded -> tvShowsUiState.tvShows
        else -> emptyList()
    }

    if (tvShowsUiState is TVShowsUiState.Error) {
        Toast.makeText(LocalContext.current, tvShowsUiState.message, Toast.LENGTH_SHORT).show()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.fillMaxHeight().padding(top = 104.dp),
            contentAlignment = Alignment.Center
        ) {
            ListView(
                buttonTitles = tvShows.map { it.name },
                onButtonPressedAt = { index ->
                    selectTVShowAt(index)
                    onTVShowPressed()
                }
            )
        }

        if (tvShowsUiState is TVShowsUiState.Loading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        IconButton(
            modifier = Modifier
                .align(alignment = Alignment.TopEnd)
                .padding(top = 50.dp, end = 50.dp),
            onClick = onSettingsPressed
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_settings_24),
                contentDescription = "Go to settings screen",
                tint = colorResource(id = R.color.moonColor)
            )
        }
    }
}