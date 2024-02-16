package io.schiar.mochannel.library.room

import io.schiar.mochannel.model.ServerURL
import io.schiar.mochannel.model.datasource.service.ServerURLService

class ServerURLRoomService(
    private val serverURLRoomDAO: ServerURLRoomDAO
): ServerURLService {
    override suspend fun create(serverURL: ServerURL) {
        serverURLRoomDAO.insert(serverURLEntity = serverURL.toServerURLEntity())
    }

    override suspend fun retrieve(): ServerURL? {
        return serverURLRoomDAO.select()?.toServerURL()
    }

    override suspend fun update(serverURL: ServerURL) {
        serverURLRoomDAO.update(serverURLEntity = serverURL.toServerURLEntity())
    }
}