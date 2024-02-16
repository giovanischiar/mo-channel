package io.schiar.mochannel.library.local

import io.schiar.mochannel.model.Episode
import io.schiar.mochannel.model.ServerURL
import io.schiar.mochannel.model.TVShow

class PreviewLocalData {
    val serverURL: ServerURL get() {
        return ServerURL(prefix = "https", url = "www.my-concerts-drive.com", port = "8080")
    }

    val tvShows: List<TVShow> get() {
        return listOf(
            TVShow(name = "U2", episodes = listOf()),
            TVShow(name = "Doja Cat", episodes = listOf()),
            TVShow(name = "Jack Harlow", episodes = listOf()),
            TVShow(name = "Twenty one Pilots", episodes = listOf()),
            TVShow(
                name = "Taylor Swift",
                episodes = listOf(
                    Episode(
                        name = "Reputation Tour 2018 Santa Clara/...Ready for It? Concert Beginning",
                        url = ""
                    ),
                    Episode(
                        name = "Reputation Tour 2018 Santa Clara/Style, Love Story, You Belong With Me, Performance snippets",
                        url = ""
                    ),
                    Episode(
                        name = "Reputation Tour 2018 Santa Clara/Singing Along with Delicate",
                        url = ""
                    ),
                    Episode(
                        name = "Reputation Tour 2018 Santa Clara/End Game Chorus Performance",
                        url = ""
                    ),
                    Episode(
                        name = "Reputation Tour 2018 Santa Clara/New Year's Day (encore)",
                        url = ""
                    ),
                    Episode(
                        name = "The Eras Tour 2024 Santa Clara/New Year's Day (encore)",
                        url = ""
                    )
                )
            ),
            TVShow(name = "Bad Bunny", episodes = listOf()),
            TVShow(name = "Metallica", episodes = listOf()),
            TVShow(name = "Maroon 5", episodes = listOf()),
            TVShow(name = "Panic! at The Disco",
                episodes = listOf(
                    Episode(
                        name = "Viva Las Vengeance Tour 2023 San Francisco/Say Amen (first song)",
                        url = ""
                    ),
                    Episode(
                        name = "Viva Las Vengeance Tour 2023 San Francisco/Viva Las Vengeance",
                        url = ""
                    ),
                    Episode(
                        name = "Pray for the Wicked Tour 2018 San Jose/Nine in the Afternoon",
                        url = ""
                    )
                )
            ),
            TVShow(name = "Mika", episodes = listOf()),
            TVShow(name = "Aqua", episodes = listOf())
        )
    }
}