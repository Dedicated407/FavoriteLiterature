package com.dedicated407.favoriteLiterature.Presentation.Repository

import androidx.lifecycle.LiveData
import com.dedicated407.favoriteLiterature.Domain.Model.Book
import com.dedicated407.favoriteLiterature.Presentation.Room.Model.BookDTO


interface IRepositoryTasks {
    fun getAllBooks(): LiveData<List<Book>>
    fun <T: Book> addBook(book: T)
    fun <T: Book> deleteBook(book: T)
}