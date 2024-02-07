package io.schiar.mochannel.model

data class TVShow(val name: String, val episodes: List<Episode>) {
    private val seasons = if (episodes.isEmpty()) {
        listOf(Season(title = "", episodes = episodes))
    } else if (!episodes[0].name.contains(char = '/')) {
        listOf(Season(title = "", episodes = episodes))
    } else {
        val seasonTitles = episodes.map { it.name.split('/')[0] }.toSortedSet()
        seasonTitles.map { seasonTitle ->
            Season(
                title = seasonTitle,
                episodes = episodes.filter {
                    val episodeSeasonName = it.name.split("/")[0]
                    episodeSeasonName == seasonTitle
                }
            )
        }
    }

    val seasonTitles: List<String> get() {
        return seasons.map { it.title }
    }

    fun episodesFromSeasonAt(index: Int): List<Episode> {
        return seasons[index].episodes
    }

    fun urlsOfEpisodesFromIndexOfSeasonAt(index: Int, episodeIndex: Int): List<String> {
        val episode = seasons[index].episodes[episodeIndex]
        val episodeAbsoluteIndex = episodes.indexOf(element = episode)
        val mutableEpisodes = episodes.toMutableList()
        val removedEpisode = mutableEpisodes.removeAt(index = episodeAbsoluteIndex)
        return (listOf(removedEpisode) +
                mutableEpisodes.subList(episodeAbsoluteIndex, mutableEpisodes.size) +
                mutableEpisodes.subList(0, episodeAbsoluteIndex)).map { it.url }
    }
}