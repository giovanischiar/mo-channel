package io.schiar.mochannel.library.retrofit

import io.schiar.mochannel.library.retrofit.json.TVShowJSON
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface TVShowsRetrofitAPI {
    @GET
    fun getTVShowsFrom(@Url url: String): Response<List<TVShowJSON>>
}