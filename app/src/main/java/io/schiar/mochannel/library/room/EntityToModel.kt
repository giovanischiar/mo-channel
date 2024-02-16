package io.schiar.mochannel.library.room

import io.schiar.mochannel.model.ServerURL

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