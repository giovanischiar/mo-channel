package io.schiar.mochannel.model.repository

import io.schiar.mochannel.model.ServerURL

fun List<((ServerURL) -> Unit)>.invokeAll(serverURL: ServerURL) {
    for (function in this) {
        function.invoke(serverURL)
    }
}