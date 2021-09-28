package com.dedicated407.favoriteLiterature.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import com.dedicated407.favoriteLiterature.Presentation.Room.Model.BookDTO
import com.dedicated407.favoriteLiterature.Presentation.Repository.Repository

class AddBookViewModel : ViewModel() {
    fun addBook(book: BookDTO) {
        Repository.getRepository().addBook(book)
    }
}