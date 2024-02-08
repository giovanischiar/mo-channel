package io.schiar.mochannel.model.datasource

import io.schiar.mochannel.model.ServerURL

interface ServerURLRetriever {
    suspend fun retrieveServerURL(): ServerURL
}