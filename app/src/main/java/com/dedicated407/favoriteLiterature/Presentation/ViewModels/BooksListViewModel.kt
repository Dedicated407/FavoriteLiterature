package com.dedicated407.favoriteLiterature.Presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.Models.BookListViewDTO
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.ServerRepository.BookRepository
import kotlinx.coroutines.launch

class BooksListViewModel : ViewModel() {
    private val bookRepository: BookRepository = BookRepository()
    private val mResponse: MutableLiveData<List<BookListViewDTO>> = MutableLiveData()

    fun getAllBooks(): LiveData<List<BookListViewDTO>> {
        viewModelScope.launch {
            val response = bookRepository.getAllBooks()
            mResponse.value = response
        }

        return mResponse
    }
}