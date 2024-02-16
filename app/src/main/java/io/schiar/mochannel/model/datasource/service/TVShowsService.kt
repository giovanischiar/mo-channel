package io.schiar.mochannel.model.datasource.service

import io.schiar.mochannel.model.TVShow

interface TVShowsService {
    fun retrieve(): List<TVShow>
}