package io.schiar.mochannel.model.datasource

import io.schiar.mochannel.model.TVShow
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("/tv-shows")
    suspend fun getTVShows(): Response<List<TVShow>>
}