package com.dedicated407.favoriteLiterature.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import com.dedicated407.favoriteLiterature.Domain.Model.Book
import com.dedicated407.favoriteLiterature.Domain.Model.User
import com.dedicated407.favoriteLiterature.Presentation.Room.Model.BookDTO
import com.dedicated407.favoriteLiterature.Presentation.Repository.Repository

class AddBookViewModel : ViewModel() {
    fun addBook(book: BookDTO) {
        Repository.getRepository().addBook(book)
    }
}