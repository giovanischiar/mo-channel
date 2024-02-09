package io.schiar.mochannel.model.repository.listeners

interface ServerURLChangedListener {
    suspend fun onServerUrlChanged()
}