package io.schiar.mochannel.model.datasource.settings

import io.schiar.mochannel.model.ServerURL

interface SettingsDataSourceable {
    suspend fun retrieveServerURL(): ServerURL
    suspend fun updateServerURLTo(newServerURL: ServerURL)
}