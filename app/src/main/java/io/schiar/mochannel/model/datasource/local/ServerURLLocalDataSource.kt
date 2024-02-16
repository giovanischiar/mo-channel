package io.schiar.mochannel.model.datasource.local

import io.schiar.mochannel.model.ServerURL
import io.schiar.mochannel.model.datasource.ServerURLDataSource

class ServerURLLocalDataSource(serverURL: ServerURL? = null): ServerURLDataSource {
    private var currentServerURL: ServerURL? = serverURL

    override suspend fun create(serverURL: ServerURL) {
        currentServerURL = serverURL
    }

    override suspend fun retrieve(): ServerURL? {
        return currentServerURL
    }

    override suspend fun update(serverURL: ServerURL) {
        currentServerURL = serverURL
    }
}