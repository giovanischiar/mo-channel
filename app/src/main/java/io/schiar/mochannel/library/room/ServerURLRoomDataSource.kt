package io.schiar.mochannel.library.room

import io.schiar.mochannel.model.ServerURL
import io.schiar.mochannel.model.datasource.ServerURLDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ServerURLRoomDataSource @Inject constructor(
    private val serverURLRoomDAO: ServerURLRoomDAO
): ServerURLDataSource {
    override suspend fun create(serverURL: ServerURL) {
        serverURLRoomDAO.insert(serverURLEntity = serverURL.toServerURLEntity())
    }

    override fun retrieve(): Flow<ServerURL?> {
        return serverURLRoomDAO.select().map { it?.toServerURL() }
    }

    override suspend fun update(serverURL: ServerURL) {
        serverURLRoomDAO.update(serverURLEntity = serverURL.toServerURLEntity())
    }
}