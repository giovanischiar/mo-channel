package io.schiar.mochannel.model.datasource

import io.schiar.mochannel.model.ServerURL

interface ServerURLDataSource {
    suspend fun create(serverURL: ServerURL)
    suspend fun retrieve(): ServerURL?
    suspend fun update(serverURL: ServerURL)
}