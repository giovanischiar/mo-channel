package io.schiar.mochannel.model.datasource.service.local

import io.schiar.mochannel.model.ServerURL
import io.schiar.mochannel.model.datasource.service.ServerURLService

class ServerURLLocalService(serverURL: ServerURL? = null): ServerURLService {
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