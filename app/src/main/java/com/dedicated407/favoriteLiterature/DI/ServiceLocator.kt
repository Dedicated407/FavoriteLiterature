package com.dedicated407.favoriteLiterature.DI

import com.dedicated407.favoriteLiterature.Presentation.Repository.Network.GoogleOAuth.GoogleSignInSignOutLogic
import com.dedicated407.favoriteLiterature.Presentation.Repository.Network.WriterAnalysis
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.IWebService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceLocator {
    private var mAnalysis: WriterAnalysis? = null
    private var mGoogleSignInSignOutLogic: GoogleSignInSignOutLogic? = null
    private var apiFavLit: IWebService? = null

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

    fun getApiFavLit(): IWebService {
        if (apiFavLit == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://1081-37-230-157-4.ngrok.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            apiFavLit = retrofit.create(IWebService::class.java)
        }
        return apiFavLit!!
    }
}