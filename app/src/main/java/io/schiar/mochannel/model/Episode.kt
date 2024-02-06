package io.schiar.mochannel.model

data class Episode(val name: String, val url: String) {
    val nameWithoutSeason: String get() {
        return if (name.contains(char = '/')) {
            name.split('/')[1]
        } else {
            name
        }
    }
}