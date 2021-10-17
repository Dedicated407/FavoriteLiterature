package com.dedicated407.favoriteLiterature.Presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.dedicated407.favoriteLiterature.DI.ServiceLocator
import com.dedicated407.favoriteLiterature.Domain.Model.Writer

class FindWriterViewModel: ViewModel() {
    private var mediatorLiveData = MediatorLiveData<List<Writer>>()

    fun setWritersList(writerName: String) {
        val serviceLocator = ServiceLocator()
        mediatorLiveData.addSource(
            serviceLocator.getInstance().getAnalysis().getWriters(writerName)
        ) {
            mediatorLiveData.value = it
        }
    }

    fun getWritersList(): LiveData<List<Writer>> {
        return mediatorLiveData
    }
}