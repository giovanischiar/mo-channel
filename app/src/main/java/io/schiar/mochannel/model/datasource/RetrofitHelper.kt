package io.schiar.mochannel.model.datasource

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    private const val baseUrl = "http://192.168.0.12:3000/"

    fun getInstance(): Retrofit {
        val gson = GsonBuilder().create()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}