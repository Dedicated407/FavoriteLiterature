package com.dedicated407.favoriteLiterature.Presentation.Repository.Mock

import androidx.lifecycle.LiveData
import com.dedicated407.favoriteLiterature.Domain.Model.Book
import com.dedicated407.favoriteLiterature.Domain.Model.User
import com.dedicated407.favoriteLiterature.Presentation.Repository.RepositoryTasks
import androidx.lifecycle.MutableLiveData
import java.time.LocalDate
import java.time.LocalDateTime


public class MockBase : RepositoryTasks {
    private var data: LiveData<List<Book>>
    private var list: ArrayList<Book> = arrayListOf()

    init {
        val book1 = Book(
            1,
            "Kotlin",
            User(
                1,
                "Ilya",
                "Tsypin"
            )
        )
        book1.author.patronymic = "Pavlovich"
        book1.author.email = "ilyaboy@list.ru"
        book1.author.phone = "+7 (800) 555-35-35"
        book1.author.lastVisit = LocalDateTime.now()
        list.add(book1)
        val book2 = Book(
            2,
            "The Bronze Horseman",
            User(
                2,
                "Alex",
                "Pushkin"
            )
        )
        book2.author.patronymic = "Sergeevich"
        list.add(book2)
        val book3 = Book(
            3,
            "War and Peace",
            User(
                3,
                "Lev",
                "Tolstoy"
            )
        )
        book3.author.patronymic = "Nikolaevich"
        list.add(book3)
        val book4 = Book(
            4,
            "Ward №6",
            User(
                4,
                "Anton",
                "Chekhov"
            )
        )
        book4.author.patronymic = "Pavlovich"
        book4.author.dateOfBirth = LocalDate.of(1860, 1, 29)
        book4.author.dateOfDeath = LocalDate.of(1904, 7,15)
        book4.description = "Ward №6. 1892. " +
                "Summary of the story. " +
                "It is read in 6 minutes, the original — 2 hours. " +
                "In the county town, in a small hospital wing, " +
                "there is ward №6 for the mentally ill."
        list.add(book4)
        data = MutableLiveData(list)
    }

    override fun getAllBooks() = data

    override fun addBook(book: Book) {
        list.add(book)

        data = MutableLiveData(list)
    }

    override fun deleteBook(book: Book) {
        list.remove(book)

        data = MutableLiveData(list)
    }

}