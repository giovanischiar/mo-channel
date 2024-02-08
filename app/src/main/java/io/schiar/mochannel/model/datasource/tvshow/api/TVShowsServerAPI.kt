package io.schiar.mochannel.model.datasource.tvshow.api

import io.schiar.mochannel.model.datasource.tvshow.api.json.TVShowJSON
import retrofit2.Response

class TVShowsServerAPI(private val api: API): TVShowsAPI() {
    override suspend fun get(url: String): Response<List<TVShowJSON>> {
        return api.get(url = url)
    }
}