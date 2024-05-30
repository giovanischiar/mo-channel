package io.schiar.mochannel.viewmodel.util

import io.schiar.mochannel.model.Episode
import io.schiar.mochannel.model.ServerURL
import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.view.viewdata.EpisodeViewData
import io.schiar.mochannel.view.viewdata.ServerURLViewData
import io.schiar.mochannel.view.viewdata.TVShowViewData

fun Episode.toViewData(): EpisodeViewData {
    return EpisodeViewData(name = nameWithoutSeason, url = url)
}

fun TVShow.toViewData(): TVShowViewData {
    return TVShowViewData(name = name, seasonTitles = seasonTitles)
}

fun List<TVShow>.toTVShowsViewDataList(): List<TVShowViewData> {
    return map { it.toViewData() }
}

fun List<Episode>.toEpisodeViewDataList(): List<EpisodeViewData> {
    return map { it.toViewData() }
}

fun ServerURL.toViewData(): ServerURLViewData {
    return ServerURLViewData(fullURL = toString(), prefix = prefix, url = url, port = port)
}