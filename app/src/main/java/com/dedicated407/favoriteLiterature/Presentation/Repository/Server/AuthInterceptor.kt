package com.dedicated407.favoriteLiterature.Presentation.Repository.Server

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val jwtToken: String?
): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        jwtToken?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }
}