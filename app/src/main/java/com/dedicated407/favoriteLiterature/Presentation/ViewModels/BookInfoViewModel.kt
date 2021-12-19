package com.dedicated407.favoriteLiterature.Presentation.ViewModels

import androidx.lifecycle.*
import com.dedicated407.favoriteLiterature.Domain.Model.Book
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.ServerRepository.BookRepository

class BookInfoViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    private val mResponse: MutableLiveData<Book> = MutableLiveData()

    fun getBook(id: String) =
    liveData(viewModelScope.coroutineContext) {
        val book = bookRepository.getBook(id)
        book.images = mutableListOf(bookRepository.downloadImage(book.id))
        mResponse.value = book

        emitSource(
            mResponse
        )
    }
}