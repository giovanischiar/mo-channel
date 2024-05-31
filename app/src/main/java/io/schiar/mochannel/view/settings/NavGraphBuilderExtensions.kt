package io.schiar.mochannel.view.settings

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.schiar.mochannel.view.shared.util.Route
import io.schiar.mochannel.viewmodel.SettingsViewModel

fun NavGraphBuilder.settingsScreen(settingsViewModel: SettingsViewModel? = null) {
    composable(route = Route.SETTINGS.id) {
        val viewModel = settingsViewModel ?: hiltViewModel()
        val serverURLsUiState by viewModel.serverURLUiStateFlow.collectAsState(
            initial = ServerURLUiState.Loading
        )

        SettingsScreen(
            serverURLUiState = serverURLsUiState,
            updatePrefixTo = viewModel::updatePrefixTo,
            updateURLTo = viewModel::updateURLTo,
            updatePortTo = viewModel::updatePortTo
        )
    }
}