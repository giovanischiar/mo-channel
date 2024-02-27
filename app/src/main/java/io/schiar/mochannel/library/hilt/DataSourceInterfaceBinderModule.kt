package io.schiar.mochannel.library.hilt

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.schiar.mochannel.library.retrofit.TVShowsRetrofitDataSource
import io.schiar.mochannel.library.room.ServerURLRoomDataSource
import io.schiar.mochannel.model.datasource.CurrentEpisodeURLsDataSource
import io.schiar.mochannel.model.datasource.CurrentTVShowDataSource
import io.schiar.mochannel.model.datasource.ServerURLDataSource
import io.schiar.mochannel.model.datasource.TVShowsDataSource
import io.schiar.mochannel.model.datasource.local.CurrentEpisodeURLsLocalDataSource
import io.schiar.mochannel.model.datasource.local.CurrentTVShowLocalDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceInterfaceBinderModule {
    @Binds
    fun bindServerURLDataSource(
        serverURLRoomDataSource: ServerURLRoomDataSource
    ): ServerURLDataSource

    @Binds
    fun bindTVShowsDataSource(
        tvShowsRetrofitDataSource: TVShowsRetrofitDataSource
    ): TVShowsDataSource

    @Binds
    @Singleton
    fun bindCurrentTVShowDataSource(
        currentTVShowLocalDataSource: CurrentTVShowLocalDataSource
    ): CurrentTVShowDataSource

    @Binds
    @Singleton
    fun bindCurrentEpisodeURLsDataSource(
        currentEpisodeURLsLocalDataSource: CurrentEpisodeURLsLocalDataSource
    ): CurrentEpisodeURLsDataSource
}