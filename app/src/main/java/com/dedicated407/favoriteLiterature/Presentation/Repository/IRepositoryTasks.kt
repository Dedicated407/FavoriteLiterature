package com.dedicated407.favoriteLiterature.Presentation.Repository

import androidx.lifecycle.LiveData
import com.dedicated407.favoriteLiterature.Domain.Model.Book


interface IRepositoryTasks {
    fun getAllBooks(): LiveData<List<Book>>
    fun <T: Book> addBook(book: T)
    fun getBook(id: String): LiveData<Book>
    fun <T: Book> deleteBook(book: T)
}