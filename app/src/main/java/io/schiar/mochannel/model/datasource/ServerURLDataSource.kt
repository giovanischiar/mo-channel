package io.schiar.mochannel.model.datasource

import io.schiar.mochannel.model.ServerURL
import kotlinx.coroutines.flow.Flow

interface ServerURLDataSource {
    suspend fun create(serverURL: ServerURL)
    fun retrieve(): Flow<ServerURL?>
    suspend fun update(serverURL: ServerURL)
}