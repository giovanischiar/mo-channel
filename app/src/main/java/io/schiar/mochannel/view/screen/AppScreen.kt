package io.schiar.mochannel.view.screen

import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.schiar.mochannel.R
import io.schiar.mochannel.viewmodel.TVShowViewModel
import io.schiar.mochannel.viewmodel.TVShowsViewModel
import io.schiar.mochannel.viewmodel.VideoViewModel

@Composable
fun AppScreen(
    tvShowsViewModel: TVShowsViewModel,
    tvShowViewModel: TVShowViewModel,
    videoViewModel: VideoViewModel,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = Modifier.background(color = colorResource(R.color.backgroundColor)),
        navController = navController,
        startDestination = "TVShows"
    ) {
        composable(route = "TVShows") {
            TVShowsScreen(
                tvShowsViewModel = tvShowsViewModel,
                onTVShowPressed = { navController.navigate(route = "TVShow") })
        }
        composable(route = "TVShow") {
            TVShowScreen(
                tvShowViewModel = tvShowViewModel,
                onVideoPressed = { navController.navigate(route = "Video") }
            )
        }
        composable(route = "Video") { VideoScreen(videoViewModel = videoViewModel) }
    }
}