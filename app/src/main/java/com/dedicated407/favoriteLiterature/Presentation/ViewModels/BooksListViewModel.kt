package com.dedicated407.favoriteLiterature.Presentation.ViewModels

import androidx.lifecycle.*
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.Models.BookListViewDTO
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.ServerRepository.BookRepository

class BooksListViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    private val mResponse: MutableLiveData<List<BookListViewDTO>> = MutableLiveData()

    fun getAllBooks(): LiveData<List<BookListViewDTO>> =
        liveData(viewModelScope.coroutineContext) {
            val books = bookRepository.getAllBooks()
            books.forEach { book ->
                book.images[0] = bookRepository.downloadImage(book.id.toString())
            }
            mResponse.value = books

            emitSource(
                mResponse
            )
        }
}