package io.schiar.mochannel.model.repository

import io.schiar.mochannel.model.ServerURL
import io.schiar.mochannel.model.datasource.ServerURLDataSource
import io.schiar.mochannel.model.datasource.local.ServerURLLocalDataSource
import io.schiar.mochannel.model.repository.listeners.ServerURLChangedOnDataSourceListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapLatest

class SettingsRepository(
    private val serverURLDataSource: ServerURLDataSource = ServerURLLocalDataSource(),
    private val serverURLChangedOnDataSourceListener: ServerURLChangedOnDataSourceListener
) {
    private var dirty: Boolean = false
    private var _serverURL = ServerURL()
    @OptIn(ExperimentalCoroutinesApi::class)
    val serverURL = serverURLDataSource.retrieve()
        .distinctUntilChanged()
        .mapLatest { serverURLFromDataSource ->
            if (serverURLFromDataSource == null) {
                serverURLDataSource.create(_serverURL)
                _serverURL
            } else {
                _serverURL = serverURLFromDataSource
                serverURLFromDataSource
            }
        }

    suspend fun updatePrefixTo(newPrefix: String) {
        serverURLDataSource.update(_serverURL.prefixUpdatedTo(newPrefix = newPrefix))
        dirty = true
    }

    suspend fun updateURLTo(newURL: String) {
        serverURLDataSource.update(_serverURL.urlUpdatedTo(newURL = newURL))
        dirty = true
    }

    suspend fun updatePortTo(newPort: String) {
        serverURLDataSource.update(_serverURL.portUpdatedTo(newPort = newPort))
        dirty = true
    }

    suspend fun serverURLChanged() {
        if (dirty) {
            serverURLChangedOnDataSourceListener.serverURLChangedOnDataSource()
            dirty = false
        }
    }
}