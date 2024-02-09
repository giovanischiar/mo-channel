package io.schiar.mochannel.model.datasource.settings

import io.schiar.mochannel.model.ServerURL
import io.schiar.mochannel.model.datasource.settings.database.ServerURLDAO
import io.schiar.mochannel.model.datasource.settings.util.ServerURLLocalDAO
import io.schiar.mochannel.model.datasource.settings.util.toServerURL
import io.schiar.mochannel.model.datasource.settings.util.toServerURLEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsDataSource(
    private val serverURLDAO: ServerURLDAO = ServerURLLocalDAO()
): SettingsDataSourceable {
    private var serverURL: ServerURL? = null

    override suspend fun retrieveServerURL(): ServerURL {
        if (serverURL == null) {
            serverURL = withContext(Dispatchers.IO) { serverURLDAO.select()?.toServerURL() }
            if (serverURL == null) {
                val emptyServerToInsert = ServerURL()
                coroutineScope {
                    launch(Dispatchers.IO) {
                        serverURLDAO.insert(
                            serverURLEntity = emptyServerToInsert.toServerURLEntity()
                        )
                    }
                }
            }
        }
        return serverURL ?: ServerURL()
    }

    override suspend fun updateServerURLTo(newServerURL: ServerURL): Unit = coroutineScope {
        serverURL = newServerURL
        launch(Dispatchers.IO) {
            serverURLDAO.update(serverURLEntity = newServerURL.toServerURLEntity())
        }
    }
}