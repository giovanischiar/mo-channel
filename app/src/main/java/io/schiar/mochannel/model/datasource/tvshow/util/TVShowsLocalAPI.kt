package io.schiar.mochannel.model.datasource.tvshow.util

import io.schiar.mochannel.model.datasource.tvshow.api.TVShowsAPI
import io.schiar.mochannel.model.datasource.tvshow.api.json.TVShowJSON
import retrofit2.Response

class TVShowsLocalAPI(private val tvShowJSONs: List<TVShowJSON> = emptyList()): TVShowsAPI() {
    override suspend fun get(url: String): Response<List<TVShowJSON>> {
        return Response.success(tvShowJSONs)
    }
}