package com.dedicated407.favoriteLiterature.Presentation.Repository

import androidx.lifecycle.LiveData
import com.dedicated407.favoriteLiterature.Domain.Model.Book


interface RepositoryTasks {
    fun getAllBooks(): LiveData<List<Book>>
    fun addBook(book: Book)
    fun deleteBook(book: Book)
}