package io.schiar.mochannel.model.datasource.service.local

import io.schiar.mochannel.model.ServerURL
import io.schiar.mochannel.model.datasource.service.ServerURLService

class ServerURLLocalService(serverURL: ServerURL? = null): ServerURLService {
    private var currentServerURL: ServerURL? = serverURL

    override fun create(serverURL: ServerURL) {
        currentServerURL = serverURL
    }

    override fun retrieve(): ServerURL? {
        return currentServerURL
    }

    override fun update(serverURL: ServerURL) {
        currentServerURL = serverURL
    }
}