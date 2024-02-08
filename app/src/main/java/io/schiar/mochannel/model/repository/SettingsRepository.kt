package io.schiar.mochannel.model.repository

import io.schiar.mochannel.model.ServerURL
import io.schiar.mochannel.model.datasource.settings.SettingsDataSource
import io.schiar.mochannel.model.datasource.settings.SettingsDataSourceable

class SettingsRepository(
    private val settingsDataSourceable: SettingsDataSourceable = SettingsDataSource(),
) {
    private var serverURLsCallbacks: MutableList<((ServerURL) -> Unit)> = mutableListOf()

    private suspend fun serverURLRetrievedFromDataSource(): ServerURL {
        return settingsDataSourceable.retrieveServerURL()
    }

    private suspend fun updateServerURLTo(newServerURL: ServerURL) {
        settingsDataSourceable.updateServerURLTo(newServerURL = newServerURL)
        serverURLsCallbacks.invokeAll(serverURL = newServerURL)
    }

    fun subscribeForServerURLs(callback: ((ServerURL) -> Unit)) {
        serverURLsCallbacks.add(callback)
    }

    suspend fun updatePrefixTo(newPrefix: String) {
        val serverURL = serverURLRetrievedFromDataSource()
        updateServerURLTo(newServerURL = serverURL.prefixUpdatedTo(newPrefix = newPrefix))
    }

    suspend fun updateURLTo(newURL: String) {
        val serverURL = serverURLRetrievedFromDataSource()
        updateServerURLTo(newServerURL = serverURL.urlUpdatedTo(newURL = newURL))
    }

    suspend fun updatePortTo(newPort: String) {
        val serverURL = serverURLRetrievedFromDataSource()
        updateServerURLTo(newServerURL = serverURL.portUpdatedTo(newPort = newPort))
    }

    suspend fun loadServerURL() {
        val serverURL = settingsDataSourceable.retrieveServerURL()
        serverURLsCallbacks.invokeAll(serverURL = serverURL)
    }
}