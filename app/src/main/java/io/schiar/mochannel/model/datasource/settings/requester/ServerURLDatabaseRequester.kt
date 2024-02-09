package io.schiar.mochannel.model.datasource.settings.requester

import io.schiar.mochannel.library.room.ServerURLEntityRequester
import io.schiar.mochannel.model.ServerURL

class ServerURLDatabaseRequester(
    private val serverURLEntityRequester: ServerURLEntityRequester
): ServerURLRequester() {
    override fun requestServerURL(): ServerURL? {
        return serverURLEntityRequester.select()?.toServerURL()
    }

    override fun insert(serverURL: ServerURL) {
        serverURLEntityRequester.insert(serverURLEntity = serverURL.toServerURLEntity())
    }

    override fun update(serverURL: ServerURL) {
        serverURLEntityRequester.update(serverURLEntity = serverURL.toServerURLEntity())
    }
}