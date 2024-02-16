package io.schiar.mochannel.model.datasource

import io.schiar.mochannel.model.ServerURL
import io.schiar.mochannel.model.datasource.service.local.ServerURLLocalService
import io.schiar.mochannel.model.datasource.service.ServerURLService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsDataSource(private val serverURLService: ServerURLService = ServerURLLocalService()) {
    private var serverURL: ServerURL? = null

    suspend fun retrieveServerURL(): ServerURL {
        if (serverURL == null) {
            serverURL = withContext(Dispatchers.IO) { serverURLService.retrieve() }
            if (serverURL == null) {
                coroutineScope {
                    launch(Dispatchers.IO) { serverURLService.create(serverURL = ServerURL()) }
                }
            }
        }
        return serverURL ?: ServerURL()
    }

    suspend fun updateServerURLTo(newServerURL: ServerURL): Unit = coroutineScope {
        serverURL = newServerURL
        launch(Dispatchers.IO) { serverURLService.update(serverURL = newServerURL) }
    }
}