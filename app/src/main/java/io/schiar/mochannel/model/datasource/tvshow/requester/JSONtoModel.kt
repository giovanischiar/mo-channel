package io.schiar.mochannel.model.datasource.tvshow.requester

import io.schiar.mochannel.library.retrofit.json.EpisodeJSON
import io.schiar.mochannel.library.retrofit.json.TVShowJSON
import io.schiar.mochannel.model.Episode
import io.schiar.mochannel.model.TVShow

fun EpisodeJSON.toEpisode(): Episode {
    return Episode(name = name, url = url)
}

fun List<EpisodeJSON>.toEpisodes(): List<Episode> {
    return map { it.toEpisode() }
}

fun TVShowJSON.toTVShow(): TVShow {
    return TVShow(name = name, episodes = episodes.toEpisodes())
}

fun List<TVShowJSON>.toTVShows(): List<TVShow> {
    return map { it.toTVShow() }
}