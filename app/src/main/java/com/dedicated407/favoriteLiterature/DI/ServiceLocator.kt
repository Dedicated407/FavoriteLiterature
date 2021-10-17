package com.dedicated407.favoriteLiterature.DI

import com.dedicated407.favoriteLiterature.Presentation.Repository.Network.WriterAnalysis

class ServiceLocator {
    private var instance: ServiceLocator? = null
    private var mAnalysis: WriterAnalysis? = null

    fun getInstance(): ServiceLocator {
        if (instance == null) {
            instance = ServiceLocator()
        }
        return instance!!
    }

    fun getAnalysis(): WriterAnalysis {
        if (mAnalysis == null) {
            mAnalysis = WriterAnalysis()
        }
        return mAnalysis!!
    }
}