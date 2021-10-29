package com.dedicated407.favoriteLiterature.Presentation.Room

import android.content.Context
import androidx.room.Room

import androidx.room.RoomDatabase

import androidx.room.Database
import com.dedicated407.favoriteLiterature.Presentation.Room.Model.BookDTO
import com.dedicated407.favoriteLiterature.Presentation.Room.DAO.IBookDAO
import com.dedicated407.favoriteLiterature.Presentation.Room.DAO.IUserDAO
import com.dedicated407.favoriteLiterature.Presentation.Room.Model.UserDTO
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@Database(entities = [BookDTO::class, UserDTO::class], version = 2, exportSchema = false)
 abstract class FavoriteLiteratureRoomDatabase : RoomDatabase() {
    abstract fun bookDao(): IBookDAO
    abstract fun userDao(): IUserDAO

    companion object {
        private var INSTANCE: FavoriteLiteratureRoomDatabase? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor: ExecutorService =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun getDatabase(context: Context): FavoriteLiteratureRoomDatabase {
            if (INSTANCE == null) {
                synchronized(FavoriteLiteratureRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            FavoriteLiteratureRoomDatabase::class.java,
                            "FL_database"
                        ).build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}


