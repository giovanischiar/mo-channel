package io.schiar.mochannel.model.datasource

import io.schiar.mochannel.model.Episode
import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.api.json.EpisodeJSON
import io.schiar.mochannel.model.datasource.api.json.TVShowJSON

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