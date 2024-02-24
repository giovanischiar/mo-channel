package io.schiar.mochannel

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import io.schiar.mochannel.library.retrofit.RetrofitAPIHelper
import io.schiar.mochannel.library.retrofit.TVShowsRetrofitAPI
import io.schiar.mochannel.library.retrofit.TVShowsRetrofitDataSource
import io.schiar.mochannel.library.room.MoChannelDatabase
import io.schiar.mochannel.library.room.ServerURLRoomDataSource
import io.schiar.mochannel.model.repository.SettingsRepository
import io.schiar.mochannel.model.repository.TVShowRepository
import io.schiar.mochannel.model.repository.TVShowsRepository
import io.schiar.mochannel.model.repository.VideoRepository

@Module
@InstallIn(ViewModelComponent::class)
internal object ViewModelModule {
    @Provides
    fun provideServerURLRoomDataSource(
        @ApplicationContext context: Context
    ): ServerURLRoomDataSource {
        return ServerURLRoomDataSource(
            MoChannelDatabase.getDatabase(context).serverURLRoomDAO()
        )
    }

    @Provides
    fun provideTVShowsDataSource(
        serverURLRoomDataSource: ServerURLRoomDataSource
    ): TVShowsRetrofitDataSource {
        return TVShowsRetrofitDataSource(
            tvShowsRetrofitAPI = RetrofitAPIHelper.getAPI().create(TVShowsRetrofitAPI::class.java),
            serverURLDataSource = serverURLRoomDataSource
        )
    }

    @Provides
    @ViewModelScoped
    fun provideSettingsRepository(serverURLRoomDataSource: ServerURLRoomDataSource): SettingsRepository {
        return SettingsRepository(serverURLDataSource = serverURLRoomDataSource)
    }

    @Provides
    @ViewModelScoped
    fun providesVideoRepository(): VideoRepository {
        return VideoRepository()
    }

    @Provides
    @ViewModelScoped
    fun provideTVShowRepository(videoRepository: VideoRepository): TVShowRepository {
        return TVShowRepository(currentEpisodeURLsListener = videoRepository)
    }

    @Provides
    @ViewModelScoped
    fun provideTVShowsRepository(
        tvShowsRetrofitDataSource: TVShowsRetrofitDataSource,
        tvShowRepository: TVShowRepository
    ): TVShowsRepository {
        return TVShowsRepository(
            tvShowsDataSource = tvShowsRetrofitDataSource,
            currentTVShowChangedListener = tvShowRepository
        )
    }
}

