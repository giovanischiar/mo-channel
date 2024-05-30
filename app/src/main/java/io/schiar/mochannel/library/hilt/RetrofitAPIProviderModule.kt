package io.schiar.mochannel.library.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.schiar.mochannel.library.retrofit.RetrofitAPIHelper
import io.schiar.mochannel.library.retrofit.TVShowsRetrofitAPI

@Module
@InstallIn(SingletonComponent::class)
internal object RetrofitAPIProviderModule {
    @Provides
    fun provideTVShowsRetrofitAPI(): TVShowsRetrofitAPI {
        return RetrofitAPIHelper.getAPI().create(TVShowsRetrofitAPI::class.java)
    }
}