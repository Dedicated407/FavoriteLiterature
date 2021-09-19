package com.dedicated407.favoriteLiterature.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import com.dedicated407.favoriteLiterature.Domain.Model.Book
import com.dedicated407.favoriteLiterature.Domain.Model.User
import com.dedicated407.favoriteLiterature.Presentation.Room.Model.BookDTO
import com.dedicated407.favoriteLiterature.Presentation.Repository.Repository

class AddBookViewModel : ViewModel() {
    fun addBook(name: String, author: User, description: String?) {

        val book = BookDTO(Book(name, author))
        if (description != null)
            book.description = description

        Repository.getRepository().addBook(book)
    }
}