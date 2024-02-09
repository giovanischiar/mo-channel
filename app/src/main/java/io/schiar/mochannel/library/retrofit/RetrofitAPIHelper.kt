package io.schiar.mochannel.library.retrofit

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class RetrofitAPIHelper {
    companion object {
        @Volatile
        private var Instance: Retrofit? = null
        fun getAPI(): Retrofit {
            val baseUrl = "http://localhost/"
            val gson = GsonBuilder().create()
            return Instance ?: synchronized(this) {
                Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
         }
    }
}