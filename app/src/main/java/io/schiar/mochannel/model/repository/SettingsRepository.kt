package io.schiar.mochannel.model.repository

import io.schiar.mochannel.model.ServerURL
import io.schiar.mochannel.model.datasource.ServerURLDataSource
import io.schiar.mochannel.model.datasource.local.ServerURLLocalDataSource
import io.schiar.mochannel.model.repository.listeners.ServerURLChangedOnDataSourceListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.properties.Delegates.observable

class SettingsRepository(
    private val serverURLDataSource: ServerURLDataSource = ServerURLLocalDataSource(),
    private val serverURLChangedListener: ServerURLChangedOnDataSourceListener
) {
    private var serverURLsCallback: ((ServerURL) -> Unit)? = null
    private var serverURL: ServerURL? by observable(initialValue = null) { _, _, newServerURL ->
        (serverURLsCallback ?: {})(newServerURL ?: return@observable)
    }

    private suspend fun createServerURLAtDataSource() = coroutineScope { 
        launch(Dispatchers.IO) {
            withContext(this.coroutineContext) {
                serverURLDataSource.create(serverURL = serverURL ?: return@withContext)
            }
            serverURLChangedListener.serverURLChangedOnDataSource()
        }
    }

    private suspend fun retrieveServerURLOrCreateAtDataSource(): ServerURL? {
        if (serverURL == null) {
            serverURL = withContext(Dispatchers.IO) { serverURLDataSource.retrieve() }
            if (serverURL == null) {
                serverURL = ServerURL()
                createServerURLAtDataSource()
            }
        }
        return serverURL
    }

    private suspend fun updateServerURLAtDataSource() = coroutineScope {
        launch(Dispatchers.IO) {
            withContext(this.coroutineContext) {
                serverURLDataSource.update(serverURL = serverURL ?: return@withContext)
            }
            serverURLChangedListener.serverURLChangedOnDataSource()
        }
    }

    private suspend fun updateServerURLAtDataSourceTo(newServerURL: ServerURL) {
        serverURL = newServerURL
        updateServerURLAtDataSource()
    }

    fun subscribeForServerURLs(callback: (ServerURL) -> Unit) { serverURLsCallback = callback }

    suspend fun updatePrefixTo(newPrefix: String) {
        val serverURL = serverURL ?: return
        updateServerURLAtDataSourceTo(newServerURL = serverURL.prefixUpdatedTo(newPrefix = newPrefix))
    }

    suspend fun updateURLTo(newURL: String) {
        val serverURL = serverURL ?: return
        updateServerURLAtDataSourceTo(newServerURL = serverURL.urlUpdatedTo(newURL = newURL))
    }

    suspend fun updatePortTo(newPort: String) {
        val serverURL = serverURL ?: return
        updateServerURLAtDataSourceTo(newServerURL = serverURL.portUpdatedTo(newPort = newPort))
    }

    suspend fun loadServerURL() { retrieveServerURLOrCreateAtDataSource() }
}