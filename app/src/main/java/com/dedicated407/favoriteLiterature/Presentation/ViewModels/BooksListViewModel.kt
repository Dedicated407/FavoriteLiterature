package com.dedicated407.favoriteLiterature.Presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dedicated407.favoriteLiterature.Domain.Model.Book
import com.dedicated407.favoriteLiterature.Presentation.Repository.Repository

class BooksListViewModel : ViewModel() {
    fun getBooksList(): LiveData<List<Book>> {
        return Repository.getBookRepository().getAllBooks()
    }

    fun deleteBook(book: Book) {
        Repository.getBookRepository().deleteBook(book)
    }
}