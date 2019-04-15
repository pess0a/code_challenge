package com.arctouch.codechallenge.infrastructure

import com.arctouch.codechallenge.api.TmdbApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class BackendIntegrator {

    companion object {

        private val api: TmdbApi = Retrofit.Builder()
                .baseUrl(TmdbApi.URL)
                .client(OkHttpClient.Builder().build())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(TmdbApi::class.java)

        fun getTmdbApi(): TmdbApi {
            return api
        }

    }


}