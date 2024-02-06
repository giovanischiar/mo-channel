package io.schiar.mochannel.model

data class TVShow(val name: String, val episodes: List<Episode>) {
    fun episodesFrom(index: Int): List<Episode> {
        val mutableEpisodes = episodes.toMutableList()
        val removedEpisode = mutableEpisodes.removeAt(index = index)
        return listOf(removedEpisode) +
                mutableEpisodes.subList(index, mutableEpisodes.size) +
                mutableEpisodes.subList(0, index)
    }
}