package io.schiar.mochannel.library.hilt

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.schiar.mochannel.library.retrofit.RetrofitAPIHelper
import io.schiar.mochannel.library.retrofit.TVShowsRetrofitAPI
import io.schiar.mochannel.library.room.MoChannelDatabase
import io.schiar.mochannel.library.room.ServerURLRoomDAO

@Module
@InstallIn(SingletonComponent::class)
internal object RoomDAORetrofitAPIProviderModule {
    @Provides
    fun provideServerURLRoomDAO(@ApplicationContext context: Context): ServerURLRoomDAO {
        return MoChannelDatabase.getDatabase(context = context).serverURLRoomDAO()
    }

    @Provides
    fun provideTVShowsRetrofitAPI(): TVShowsRetrofitAPI {
        return RetrofitAPIHelper.getAPI().create(TVShowsRetrofitAPI::class.java)
    }
}