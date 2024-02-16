package io.schiar.mochannel.model.datasource.service

import io.schiar.mochannel.model.ServerURL

interface ServerURLService {
    suspend fun create(serverURL: ServerURL)
    suspend fun retrieve(): ServerURL?
    suspend fun update(serverURL: ServerURL)
}