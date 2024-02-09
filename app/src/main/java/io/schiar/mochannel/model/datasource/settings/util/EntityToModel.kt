package io.schiar.mochannel.model.datasource.settings.util

import io.schiar.mochannel.model.ServerURL
import io.schiar.mochannel.model.datasource.settings.database.ServerURLEntity

fun ServerURLEntity.toServerURL(): ServerURL {
    return ServerURL(
        prefix = prefix,
        url = url,
        port = port
    )
}

fun ServerURL.toServerURLEntity(): ServerURLEntity {
    return ServerURLEntity(
        prefix = prefix,
        url = url,
        port = port
    )
}