package io.schiar.mochannel.model.datasource.local

import io.schiar.mochannel.model.TVShow
import io.schiar.mochannel.model.datasource.CurrentTVShowDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class CurrentTVShowLocalDataSource @Inject constructor(): CurrentTVShowDataSource {
    private val currentTVShow = MutableStateFlow<TVShow?>(value = null)

    override fun retrieve(): Flow<TVShow?> { return currentTVShow }
    override fun update(tvShow: TVShow) { currentTVShow.update { tvShow } }
}