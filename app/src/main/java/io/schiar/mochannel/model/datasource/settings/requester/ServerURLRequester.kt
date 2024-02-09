package io.schiar.mochannel.model.datasource.settings.requester

import io.schiar.mochannel.model.ServerURL

abstract class ServerURLRequester() {
    abstract fun requestServerURL(): ServerURL?
    abstract fun insert(serverURL: ServerURL)
    abstract fun update(serverURL: ServerURL)
}