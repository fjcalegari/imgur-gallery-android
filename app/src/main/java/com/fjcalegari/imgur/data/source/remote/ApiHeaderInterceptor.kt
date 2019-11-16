package com.fjcalegari.imgur.data.source.remote

import com.fjcalegari.imgur.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ApiHeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val requestBuilder = original.newBuilder()
            .header("Authorization", "Client-ID ${BuildConfig.API_CLIENT_ID_KEY}")

        val request = requestBuilder.method(original.method(), original.body()).build()

        return chain.proceed(request)
    }
}
