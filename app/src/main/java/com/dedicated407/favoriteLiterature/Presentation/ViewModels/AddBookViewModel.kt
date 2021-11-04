package com.dedicated407.favoriteLiterature.Presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dedicated407.favoriteLiterature.Domain.Model.Book
import com.dedicated407.favoriteLiterature.Presentation.Repository.Repository
import com.dedicated407.favoriteLiterature.Presentation.Room.Model.BookDTO

class AddBookViewModel : ViewModel() {
    private val mImage = MutableLiveData<String?>()
    val image: LiveData<String?>
        get() = mImage

    fun addBook(book: Book) {
        Repository.getBookRepository().addBook(BookDTO(book))
    }

    fun setImage(image: String?) {
        mImage.value = image
    }

}