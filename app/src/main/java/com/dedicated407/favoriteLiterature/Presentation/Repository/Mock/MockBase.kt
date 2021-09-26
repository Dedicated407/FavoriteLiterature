package com.dedicated407.favoriteLiterature.Presentation.Repository.Mock

import com.dedicated407.favoriteLiterature.Domain.Model.Book
import com.dedicated407.favoriteLiterature.Domain.Model.User
import com.dedicated407.favoriteLiterature.Presentation.Repository.IRepositoryTasks
import androidx.lifecycle.MutableLiveData


class MockBase : IRepositoryTasks {
    private var data: MutableLiveData<List<Book>>
    private var list: ArrayList<Book> = arrayListOf()

    init {
        val book1 = Book(
            "Kotlin",
            User(
                "Ilya",
                "Tsypin",
                "Pavlovich",
                "+7 (800) 555-35-35",
                "ilyaboy@list.ru"
            )
        )
        list.add(book1)


        val book2 = Book(
            "The Bronze Horseman",
            User(
                "Alex",
                "Pushkin",
                "Sergeevich"
            )
        )
        list.add(book2)


        val book3 = Book(
            "War and Peace",
            User(
                "Lev",
                "Tolstoy",
                "Nikolaevich"
            )
        )
        list.add(book3)
        
        val book4 = Book(
            "Ward №6",
            User(
                "Anton",
                "Chekhov",
                "Pavlovich"
            ),
            "Ward №6. 1892. " +
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

}