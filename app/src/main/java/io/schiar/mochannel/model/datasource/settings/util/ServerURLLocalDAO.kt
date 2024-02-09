package io.schiar.mochannel.model.datasource.settings.util

import io.schiar.mochannel.model.datasource.settings.database.ServerURLDAO
import io.schiar.mochannel.model.datasource.settings.database.ServerURLEntity

class ServerURLLocalDAO(serverURLEntity: ServerURLEntity? = null): ServerURLDAO {
    private var currentServerURLEntity: ServerURLEntity? = serverURLEntity

    override fun insert(serverURLEntity: ServerURLEntity): Long {
        currentServerURLEntity = serverURLEntity
        return 1
    }

    override fun update(serverURLEntity: ServerURLEntity) {
        currentServerURLEntity = serverURLEntity
    }

    override fun select(): ServerURLEntity? {
        return currentServerURLEntity
    }
}