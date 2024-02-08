package io.schiar.mochannel.model.repository

import io.schiar.mochannel.model.ServerURL

interface SettingsRepository {
    fun subscribeForServerURLs(callback: ((ServerURL) -> Unit))
    suspend fun updatePrefixTo(newPrefix: String)
    suspend fun updateURLTo(newURL: String)
    suspend fun updatePortTo(newPort: String)
    suspend fun loadServerURL()
}