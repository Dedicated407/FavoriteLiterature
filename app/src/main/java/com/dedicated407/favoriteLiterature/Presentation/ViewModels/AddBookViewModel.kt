package com.dedicated407.favoriteLiterature.Presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dedicated407.favoriteLiterature.Presentation.Room.Model.BookDTO
import com.dedicated407.favoriteLiterature.Presentation.Repository.Repository

class AddBookViewModel : ViewModel() {
    private val mImage = MutableLiveData<String?>()
    val image: LiveData<String?>
        get() = mImage

    fun addBook(book: BookDTO) {
        Repository.getBookRepository().addBook(book)
    }

    fun setImage(image: String?) {
        mImage.value = image
    }

}