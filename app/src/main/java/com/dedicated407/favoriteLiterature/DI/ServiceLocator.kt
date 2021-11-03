package com.dedicated407.favoriteLiterature.DI

import com.dedicated407.favoriteLiterature.Presentation.Repository.Network.GoogleOAuth.GoogleSignInSignOutLogic
import com.dedicated407.favoriteLiterature.Presentation.Repository.Network.WriterAnalysis

class ServiceLocator {
    private var mAnalysis: WriterAnalysis? = null
    private var mGoogleSignInSignOutLogic: GoogleSignInSignOutLogic? = null

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
}