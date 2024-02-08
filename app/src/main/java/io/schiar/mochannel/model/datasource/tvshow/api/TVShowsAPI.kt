package io.schiar.mochannel.model.datasource.tvshow.api

import io.schiar.mochannel.model.datasource.tvshow.api.json.TVShowJSON
import retrofit2.Response

abstract class TVShowsAPI {
    abstract suspend fun get(url: String): Response<List<TVShowJSON>>

    suspend fun getTVShows(url: String): Response<List<TVShowJSON>> {
        return get(url = "$url/tv-shows")
    }
}