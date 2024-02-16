package io.schiar.mochannel.model.datasource.service

import io.schiar.mochannel.model.ServerURL

interface ServerURLService {
    fun create(serverURL: ServerURL)
    fun retrieve(): ServerURL?
    fun update(serverURL: ServerURL)
}