package com.dedicated407.favoriteLiterature.Presentation.Repository

import android.app.Application
import com.dedicated407.favoriteLiterature.Presentation.Repository.Mock.BookMockBase
import com.dedicated407.favoriteLiterature.Presentation.Room.BookRepository
import com.dedicated407.favoriteLiterature.Presentation.Room.FavoriteLiteratureRoomDatabase


class Repository(application: Application) {

    init {
        if (bookRepository == null) {
            bookRepository = BookRepository(
                FavoriteLiteratureRoomDatabase.getDatabase(
                application.applicationContext
            ))
        }
    }

    companion object{
        private var bookRepository: IBookRepository? = null

        fun getBookRepository(): IBookRepository{
            if (bookRepository == null) {
                bookRepository = BookMockBase()
            }
            return bookRepository as IBookRepository
        }
    }
}