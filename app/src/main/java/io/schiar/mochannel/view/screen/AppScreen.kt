package io.schiar.mochannel.view.screen

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import io.schiar.mochannel.R
import io.schiar.mochannel.viewmodel.SettingsViewModel
import io.schiar.mochannel.viewmodel.TVShowViewModel
import io.schiar.mochannel.viewmodel.TVShowsViewModel
import io.schiar.mochannel.viewmodel.VideoViewModel

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun AppScreen(
    settingsViewModel: SettingsViewModel,
    tvShowsViewModel: TVShowsViewModel,
    tvShowViewModel: TVShowViewModel,
    videoViewModel: VideoViewModel,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val serverURL by settingsViewModel.serverURL.collectAsState()
    LaunchedEffect(serverURL) {
        val serverURLFullURL = (serverURL ?: return@LaunchedEffect).fullURL
        val isServerURLValid = Patterns.WEB_URL.matcher(serverURLFullURL).matches()
        if (!isServerURLValid && navBackStackEntry?.destination?.route != "Settings") {
            navController.navigate("Settings")
        }
    }

    Box {
        NavHost(
            modifier = Modifier.background(color = colorResource(R.color.backgroundColor)),
            navController = navController,
            startDestination = "TVShows"
        ) {
            composable(route = "Settings") { SettingsScreen(settingsViewModel = settingsViewModel) }
            composable(route = "TVShows") {
                TVShowsScreen(
                    tvShowsViewModel = tvShowsViewModel,
                    onTVShowPressed = { navController.navigate(route = "TVShow") }
                )
            }
            composable(route = "TVShow") {
                TVShowScreen(
                    tvShowViewModel = tvShowViewModel,
                    onVideoPressed = { navController.navigate(route = "Video") }
                )
            }
            composable(route = "Video") { VideoScreen(videoViewModel = videoViewModel) }
        }

        if (navBackStackEntry?.destination?.route == "TVShows") {
            IconButton(
                modifier = Modifier
                    .align(alignment = Alignment.TopEnd)
                    .padding(top = 50.dp, end = 50.dp),
                onClick = { navController.navigate(route = "Settings") }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_settings_24),
                    contentDescription = "Go to settings screen",
                    tint = colorResource(id = R.color.moonColor)
                )
            }
        }
    }
}