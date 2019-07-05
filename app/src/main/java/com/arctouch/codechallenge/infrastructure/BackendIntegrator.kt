package com.arctouch.codechallenge.infrastructure

import com.arctouch.codechallenge.BuildConfig
import com.arctouch.codechallenge.api.TmdbApi
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Url
import java.net.URL

class BackendIntegrator {

    companion object {

        private var clientInterceptor = Interceptor { chain ->
            var request = chain.request()
            val url = request.url().newBuilder()
                    .addQueryParameter("api_key",BuildConfig.API_KEY).build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }

        private var client: OkHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(clientInterceptor)
                .build()

        private val api: TmdbApi = Retrofit.Builder()
                .baseUrl(TmdbApi.URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(TmdbApi::class.java)

        fun getTmdbApi(): TmdbApi {
            return api
        }


    }


}