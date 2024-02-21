package io.schiar.mochannel.library.retrofit

import io.schiar.mochannel.library.retrofit.json.TVShowJSON
import retrofit2.http.GET
import retrofit2.http.Url

interface TVShowsRetrofitAPI {
    @GET
    suspend fun getTVShowsFrom(@Url url: String): List<TVShowJSON>
}