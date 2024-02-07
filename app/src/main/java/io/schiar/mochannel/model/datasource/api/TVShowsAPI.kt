package io.schiar.mochannel.model.datasource.api

import io.schiar.mochannel.model.datasource.api.json.TVShowJSON
import retrofit2.Response
import retrofit2.http.GET

interface TVShowsAPI {
    @GET("/tv-shows")
    suspend fun getTVShows(): Response<List<TVShowJSON>>
}