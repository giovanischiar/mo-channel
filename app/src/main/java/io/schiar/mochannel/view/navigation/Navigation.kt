package io.schiar.mochannel.view.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.schiar.mochannel.R
import io.schiar.mochannel.view.settings.settingsScreen
import io.schiar.mochannel.view.shared.util.Route
import io.schiar.mochannel.view.tvshow.tvShowScreen
import io.schiar.mochannel.view.tvshows.tvShowsScreen
import io.schiar.mochannel.view.video.videoScreen
import io.schiar.mochannel.viewmodel.SettingsViewModel
import io.schiar.mochannel.viewmodel.TVShowViewModel
import io.schiar.mochannel.viewmodel.TVShowsViewModel
import io.schiar.mochannel.viewmodel.VideoViewModel

@Composable
fun Navigation(
    tvShowsViewModel: TVShowsViewModel? = null,
    settingsViewModel: SettingsViewModel? = null,
    tvShowViewModel: TVShowViewModel? = null,
    videoViewModel: VideoViewModel? = null
) {
    val navController = rememberNavController()

    Box {
        NavHost(
            modifier = Modifier.background(color = colorResource(R.color.backgroundColor)),
            navController = navController,
            startDestination = Route.TV_SHOWS.id
        ) {
            settingsScreen(settingsViewModel = settingsViewModel)

            tvShowsScreen(
                tvShowsViewModel = tvShowsViewModel,
                onNavigateToSettings = { navController.navigate(route = Route.SETTINGS.id) },
                onNavigateToTVShow = { navController.navigate(route = Route.TV_SHOW.id) }
            )

            tvShowScreen(
                tvShowViewModel = tvShowViewModel,
                onNavigateToVideo = { navController.navigate(route = Route.VIDEO.id) }
            )

            videoScreen(videoViewModel = videoViewModel)
        }
    }
}