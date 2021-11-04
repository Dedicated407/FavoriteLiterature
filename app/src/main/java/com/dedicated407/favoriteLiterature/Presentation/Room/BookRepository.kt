package com.dedicated407.favoriteLiterature.Presentation.Room

import androidx.lifecycle.LiveData
import com.dedicated407.favoriteLiterature.Domain.Model.Book
import com.dedicated407.favoriteLiterature.Presentation.Room.Model.BookDTO
import com.dedicated407.favoriteLiterature.Presentation.Repository.IBookRepository
import com.dedicated407.favoriteLiterature.Presentation.Room.DAO.IBookDAO


class BookRepository(db: FavoriteLiteratureRoomDatabase) : IBookRepository {
    private var mIBookDao: IBookDAO = db.bookDao()
    private var mAllBooks: LiveData<List<Book>> = mIBookDao.getAllBooks() as LiveData<List<Book>>

    override fun getAllBooks(): LiveData<List<Book>> = mAllBooks

    override fun <T : Book> addBook(book: T) {
        FavoriteLiteratureRoomDatabase.databaseWriteExecutor.execute {
            mIBookDao.addBook(book as BookDTO)
        }
    }

    override fun <T : Book> deleteBook(book: T) {
        FavoriteLiteratureRoomDatabase.databaseWriteExecutor.execute {
            mIBookDao.deleteBook(book as BookDTO)
        }
    }

    override fun getBook(id: String): LiveData<Book> = mIBookDao.getBook(id) as LiveData<Book>
}

