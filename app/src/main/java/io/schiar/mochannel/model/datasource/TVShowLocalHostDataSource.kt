package io.schiar.mochannel.model.datasource

import io.schiar.mochannel.model.TVShow

class TVShowLocalHostDataSource: TVShowDataSource {
    override suspend fun fetchTVShows(): List<TVShow> {
        val api = RetrofitHelper.getInstance().create(Api::class.java)
        val result = api.getTVShows()
        return result.body() ?: emptyList()
    }
}