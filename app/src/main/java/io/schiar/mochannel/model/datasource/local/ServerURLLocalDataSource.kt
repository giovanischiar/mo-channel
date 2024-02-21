package io.schiar.mochannel.model.datasource.local

import io.schiar.mochannel.model.ServerURL
import io.schiar.mochannel.model.datasource.ServerURLDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ServerURLLocalDataSource(serverURL: ServerURL? = null): ServerURLDataSource {
    private var _currentServerURL = MutableStateFlow(value = serverURL)
    private var currentServerURL: StateFlow<ServerURL?> = _currentServerURL

    override suspend fun create(serverURL: ServerURL) {
        _currentServerURL.update { serverURL }
    }

    override fun retrieve(): Flow<ServerURL?> {
        return currentServerURL
    }

    override suspend fun update(serverURL: ServerURL) {
        _currentServerURL.update { serverURL }
    }
}