package com.dedicated407.favoriteLiterature.Presentation.Room

import androidx.lifecycle.LiveData
import android.app.Application
import com.dedicated407.favoriteLiterature.Domain.Model.Book
import com.dedicated407.favoriteLiterature.Presentation.Room.Model.BookDTO
import com.dedicated407.favoriteLiterature.Presentation.Repository.IRepositoryTasks
import com.dedicated407.favoriteLiterature.Presentation.Room.DAO.IBookDAO


class BookRepository(application: Application) : IRepositoryTasks {
    private var mIBookDao: IBookDAO
    private var mAllBooks: LiveData<List<Book>>

    init {
        var db = BookRoomDatabase.getDatabase(application.applicationContext)
        mIBookDao = db.bookDao()
        mAllBooks = mIBookDao.getAllBooks() as LiveData<List<Book>>
    }

    override fun getAllBooks(): LiveData<List<Book>> = mAllBooks

    override fun <T : Book> addBook(book: T) {
        BookRoomDatabase.databaseWriteExecutor.execute {
            mIBookDao.addBook(book as BookDTO)
        }
    }

    override fun <T : Book> deleteBook(book: T) {
        BookRoomDatabase.databaseWriteExecutor.execute {
            mIBookDao.deleteBook(book as BookDTO)
        }
    }

}

