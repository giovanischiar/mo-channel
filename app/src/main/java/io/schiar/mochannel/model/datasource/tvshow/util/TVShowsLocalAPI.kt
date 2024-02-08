package io.schiar.mochannel.model.datasource.tvshow.util

import io.schiar.mochannel.model.datasource.tvshow.api.TVShowsAPI
import io.schiar.mochannel.model.datasource.tvshow.api.json.TVShowJSON
import retrofit2.Response

class TVShowsLocalAPI: TVShowsAPI() {
    override suspend fun get(url: String): Response<List<TVShowJSON>> {
        return Response.success(listOf())
    }
}