package io.schiar.mochannel.model.repository.listeners

import io.schiar.mochannel.model.TVShow

interface CurrentTVShowListener {
    fun currentTVShowChangedTo(newCurrentTVShow: TVShow)
}