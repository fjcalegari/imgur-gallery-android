package com.fjcalegari.imgur.di

import com.fjcalegari.imgur.BuildConfig
import com.fjcalegari.imgur.data.remote.ApiHeaderInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    companion object {
        private const val BASE_URL = "https://api.imgur.com/3/"
    }

    @Provides
    fun provideHttpClient(apiHeaderInterceptor: ApiHeaderInterceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(apiHeaderInterceptor)
            if (BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            }
        }.build()
    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Provides
    fun provideRetrofit(httpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideApiHeaderInterceptor(): ApiHeaderInterceptor =
        ApiHeaderInterceptor(BuildConfig.API_CLIENT_ID_KEY)

}
