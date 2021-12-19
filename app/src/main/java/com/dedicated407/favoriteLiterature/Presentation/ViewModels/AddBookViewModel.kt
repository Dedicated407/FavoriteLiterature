package com.dedicated407.favoriteLiterature.Presentation.ViewModels

import android.util.Log
import androidx.lifecycle.*
import com.dedicated407.favoriteLiterature.Domain.Model.Book
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.ServerRepository.BookRepository
import kotlinx.coroutines.launch

class AddBookViewModel(
    private val bookRepository: BookRepository
) : ViewModel() {
    private val mImage = MutableLiveData<String?>()
    val image: LiveData<String?>
        get() = mImage

    fun addBook(book: Book) =
        viewModelScope.launch {
            try {
                bookRepository.addBook(book)
                Log.d("AddedBook", "The book was added correctly!")
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }

    fun setImage(image: String?) {
        mImage.value = image
    }

}