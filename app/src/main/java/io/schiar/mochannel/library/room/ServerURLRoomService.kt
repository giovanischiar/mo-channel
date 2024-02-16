package io.schiar.mochannel.library.room

import io.schiar.mochannel.model.ServerURL
import io.schiar.mochannel.model.datasource.service.ServerURLService

class ServerURLRoomService(
    private val serverURLRoomDAO: ServerURLRoomDAO
): ServerURLService {
    override fun create(serverURL: ServerURL) {
        serverURLRoomDAO.insert(serverURLEntity = serverURL.toServerURLEntity())
    }

    override fun retrieve(): ServerURL? {
        return serverURLRoomDAO.select()?.toServerURL()
    }

    override fun update(serverURL: ServerURL) {
        serverURLRoomDAO.update(serverURLEntity = serverURL.toServerURLEntity())
    }
}