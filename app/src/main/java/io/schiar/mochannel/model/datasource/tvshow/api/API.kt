package io.schiar.mochannel.model.datasource.tvshow.api

import io.schiar.mochannel.model.datasource.tvshow.api.json.TVShowJSON
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface API {
    @GET
    suspend fun get(@Url url: String): Response<List<TVShowJSON>>
}