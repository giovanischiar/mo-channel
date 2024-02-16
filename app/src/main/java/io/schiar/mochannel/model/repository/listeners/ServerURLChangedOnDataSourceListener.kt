package io.schiar.mochannel.model.repository.listeners

interface ServerURLChangedOnDataSourceListener {
    suspend fun serverURLChangedOnDataSource()
}