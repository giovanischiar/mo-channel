package io.schiar.mochannel.model.datasource.settings

import io.schiar.mochannel.model.datasource.settings.database.ServerURLDAO
import io.schiar.mochannel.model.datasource.settings.database.ServerURLEntity

class ServerURLLocalDAO: ServerURLDAO {
    private var currentServerURLEntity: ServerURLEntity? = null

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