package com.dedicated407.favoriteLiterature.Presentation.Room

import android.content.Context
import androidx.room.Room

import androidx.room.RoomDatabase

import androidx.room.Database
import com.dedicated407.favoriteLiterature.Presentation.Room.Model.BookDTO
import com.dedicated407.favoriteLiterature.Presentation.Room.DAO.IBookDAO
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@Database(entities = [BookDTO::class], version = 2, exportSchema = false)
 abstract class BookRoomDatabase : RoomDatabase() {
    abstract fun bookDao(): IBookDAO

    companion object {
        private var INSTANCE: BookRoomDatabase? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor: ExecutorService =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun getDatabase(context: Context): BookRoomDatabase {
            if (INSTANCE == null) {
                synchronized(BookRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            BookRoomDatabase::class.java,
                            "FL_database"
                        ).allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}


