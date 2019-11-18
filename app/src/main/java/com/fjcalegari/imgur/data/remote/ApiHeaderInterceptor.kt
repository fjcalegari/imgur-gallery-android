package com.fjcalegari.imgur.data.remote

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class ApiHeaderInterceptor(
    private val clientId: String
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val requestBuilder = original.newBuilder()
            .header("Authorization", "Client-ID $clientId")

        val request = requestBuilder.method(original.method(), original.body()).build()

        return chain.proceed(request)
    }
}
