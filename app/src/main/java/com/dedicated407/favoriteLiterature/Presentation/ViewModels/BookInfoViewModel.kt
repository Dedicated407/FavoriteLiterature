package com.dedicated407.favoriteLiterature.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import com.dedicated407.favoriteLiterature.Presentation.Repository.Repository

class BookInfoViewModel : ViewModel() {
    fun getBook(id: String) = Repository.getRepository().getBook(id)
}