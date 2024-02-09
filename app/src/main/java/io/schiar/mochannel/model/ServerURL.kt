package io.schiar.mochannel.model

data class ServerURL(val prefix: String = "HTTP", val url: String = "", val port: String = "") {
    fun prefixUpdatedTo(newPrefix: String): ServerURL {
        return ServerURL(prefix = newPrefix, url = url, port = port)
    }

    fun urlUpdatedTo(newURL: String): ServerURL {
        return ServerURL(
            prefix = prefix,
            url = Regex(pattern = "\\.+").replace(newURL, replacement = "."),
            port = port
        )
    }

    fun portUpdatedTo(newPort: String): ServerURL {
        return ServerURL(
            prefix = prefix,
            url = url,
            port = newPort
                .replace(oldValue = ".", newValue = "")
                .replace(oldValue = ",", newValue = "")
                .replace(oldValue = "-", newValue = "")
                .replace(oldValue = " ", newValue = "")
        )
    }

    fun isEmpty(): Boolean { return url.isEmpty() }

    fun toStringWithRoute(route: String): String {
        return "$this/$route"
    }

    override fun toString(): String {
        return "${if (prefix == "") "" else "$prefix://"}$url${if (port == "") "" else ":$port"}"
    }
}