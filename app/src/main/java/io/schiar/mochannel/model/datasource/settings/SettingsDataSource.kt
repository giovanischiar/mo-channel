package io.schiar.mochannel.model.datasource.settings

import io.schiar.mochannel.model.ServerURL

class SettingsDataSource: SettingsDataSourceable {
    private var serverURL: ServerURL? = null

    override suspend fun retrieveServerURL(): ServerURL {
        if (serverURL == null) {
            val defaultServer = ServerURL()
            serverURL = defaultServer
            return defaultServer
        }
        return serverURL ?: ServerURL()
    }

    override suspend fun updateServerURLTo(newServerURL: ServerURL) {
        serverURL = newServerURL
    }
}