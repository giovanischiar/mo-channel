package io.schiar.mochannel.model.datasource

import io.schiar.mochannel.model.datasource.api.TVShowsAPI
import io.schiar.mochannel.model.datasource.api.json.TVShowJSON
import retrofit2.Response

class TVShowsLocalAPI: TVShowsAPI {
    override suspend fun getTVShows(): Response<List<TVShowJSON>> {
        return Response.success(listOf())
    }
}