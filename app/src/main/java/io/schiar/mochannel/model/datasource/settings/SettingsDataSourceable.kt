package io.schiar.mochannel.model.datasource.settings

import io.schiar.mochannel.model.ServerURL
import io.schiar.mochannel.model.datasource.ServerURLRetriever

interface SettingsDataSourceable: ServerURLRetriever {
    suspend fun updateServerURLTo(newServerURL: ServerURL)
}