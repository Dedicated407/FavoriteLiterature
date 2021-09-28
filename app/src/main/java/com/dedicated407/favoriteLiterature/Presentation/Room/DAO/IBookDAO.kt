package com.dedicated407.favoriteLiterature.Presentation.Room.DAO

import androidx.lifecycle.LiveData

import androidx.room.Delete

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dedicated407.favoriteLiterature.Presentation.Room.Model.BookDTO

@Dao
interface IBookDAO {
    @Insert
    fun addBook(book: BookDTO)

    @Delete
    fun deleteBook(book: BookDTO)

    @Query("SELECT * FROM book")
    fun getAllBooks(): LiveData<List<BookDTO>>

    @Query("SELECT * FROM book WHERE id=:id")
    fun getBook(id: String): LiveData<BookDTO>
}

