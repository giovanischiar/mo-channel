package io.schiar.mochannel.model.datasource.settings.requester

import io.schiar.mochannel.model.ServerURL

class ServerURLMemoryRequester(serverURL: ServerURL? = null): ServerURLRequester() {
    private var currentServerURL: ServerURL? = serverURL

    override fun insert(serverURL: ServerURL) {
        currentServerURL = serverURL
    }

    override fun requestServerURL(): ServerURL? {
        return currentServerURL
    }

    override fun update(serverURL: ServerURL) {
        currentServerURL = serverURL
    }
}