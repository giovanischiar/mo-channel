package io.schiar.mochannel.model.datasource.settings

import io.schiar.mochannel.model.ServerURL
import io.schiar.mochannel.model.datasource.settings.requester.ServerURLMemoryRequester
import io.schiar.mochannel.model.datasource.settings.requester.ServerURLRequester
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsDataSource(
    private val serverURLRequester: ServerURLRequester = ServerURLMemoryRequester()
): SettingsDataSourceable {
    private var serverURL: ServerURL? = null

    override suspend fun retrieveServerURL(): ServerURL {
        if (serverURL == null) {
            serverURL = withContext(Dispatchers.IO) { serverURLRequester.requestServerURL() }
            if (serverURL == null) {
                coroutineScope {
                    launch(Dispatchers.IO) { serverURLRequester.insert(serverURL = ServerURL()) }
                }
            }
        }
        return serverURL ?: ServerURL()
    }

    override suspend fun updateServerURLTo(newServerURL: ServerURL): Unit = coroutineScope {
        serverURL = newServerURL
        launch(Dispatchers.IO) { serverURLRequester.update(serverURL = newServerURL) }
    }
}