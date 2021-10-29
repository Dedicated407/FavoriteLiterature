package com.dedicated407.favoriteLiterature.Presentation.Repository.Mock

import androidx.lifecycle.LiveData
import com.dedicated407.favoriteLiterature.Domain.Model.Book
import com.dedicated407.favoriteLiterature.Domain.Model.User
import com.dedicated407.favoriteLiterature.Presentation.Repository.IBookRepository
import androidx.lifecycle.MutableLiveData


class BookMockBase : IBookRepository {
    private var data: MutableLiveData<List<Book>>
    private var list: ArrayList<Book> = arrayListOf()

    init {
        val book1 = Book(
            name = "Kotlin",
            author = User(
                name = "Ilya",
                lastName = "Tsypin",
                patronymic = "Pavlovich",
                phone = "+7 (800) 555-35-35",
                email = "ilyaboy@list.ru"
            )
        )
        list.add(book1)


        val book2 = Book(
            name = "The Bronze Horseman",
            author = User(
                name = "Alex",
                lastName = "Pushkin",
                patronymic = "Sergeevich"
            )
        )
        list.add(book2)


        val book3 = Book(
            name = "War and Peace",
            author = User(
                name = "Lev",
                lastName = "Tolstoy",
                patronymic = "Nikolaevich"
            )
        )
        list.add(book3)
        
        val book4 = Book(
            name = "Ward №6",
            author = User(
                name = "Anton",
                lastName = "Chekhov",
                patronymic = "Pavlovich"
            ),
            description = "Ward №6. 1892. " +
                    "Summary of the story. " +
                    "It is read in 6 minutes, the original — 2 hours. " +
                    "In the county town, in a small hospital wing, " +
                    "there is ward №6 for the mentally ill."
        )
        list.add(book4)

        data = MutableLiveData(list)
    }

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