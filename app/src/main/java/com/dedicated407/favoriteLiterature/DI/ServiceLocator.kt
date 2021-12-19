package com.dedicated407.favoriteLiterature.DI

import com.dedicated407.favoriteLiterature.Presentation.Repository.Network.GoogleOAuth.GoogleSignInSignOutLogic
import com.dedicated407.favoriteLiterature.Presentation.Repository.Network.WriterAnalysis
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.AuthInterceptor
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.IWebService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceLocator {
    private var mAnalysis: WriterAnalysis? = null
    private var mGoogleSignInSignOutLogic: GoogleSignInSignOutLogic? = null
    private var apiFavLit: IWebService? = null
    var jwtToken: String? = null

    companion object {
        private var instance: ServiceLocator? = null

        fun getInstance(): ServiceLocator {
            if (instance == null) {
                instance = ServiceLocator()
            }
            return instance!!
        }
    }

    fun getAnalysis(): WriterAnalysis {
        if (mAnalysis == null) {
            mAnalysis = WriterAnalysis()
        }
        return mAnalysis!!
    }

    fun getGoogleSignIn(): GoogleSignInSignOutLogic {
        if (mGoogleSignInSignOutLogic == null) {
            mGoogleSignInSignOutLogic = GoogleSignInSignOutLogic()
        }
        return mGoogleSignInSignOutLogic!!
    }

    private fun okHttpClient()
        = OkHttpClient.Builder().addInterceptor(AuthInterceptor(jwtToken)).build()

    fun getApiFavLit(): IWebService {
        if (apiFavLit == null || jwtToken != null) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://46d1-79-139-177-81.ngrok.io")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient())
                .build()

            apiFavLit = retrofit.create(IWebService::class.java)
        }
        return apiFavLit!!
    }
}