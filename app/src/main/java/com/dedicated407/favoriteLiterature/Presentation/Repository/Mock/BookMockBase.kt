package com.dedicated407.favoriteLiterature.Presentation.Repository.Mock

import androidx.lifecycle.LiveData
import com.dedicated407.favoriteLiterature.Domain.Model.Book
import com.dedicated407.favoriteLiterature.Presentation.Repository.IBookRepository
import androidx.lifecycle.MutableLiveData


class BookMockBase : IBookRepository {
    private lateinit var data: MutableLiveData<List<Book>>
    private var list: ArrayList<Book> = arrayListOf()


    override fun getAllBooks(): MutableLiveData<List<Book>> = data

    override fun <T : Book> addBook(book: T) {
        list.add(book)

        data = MutableLiveData(list)
    }

    override fun <T : Book> deleteBook(book: T) {
        list.remove(book)

        data = MutableLiveData(list)
    }

    override fun getBook(id: String): LiveData<Book> {
        TODO("Not yet implemented")
    }
}