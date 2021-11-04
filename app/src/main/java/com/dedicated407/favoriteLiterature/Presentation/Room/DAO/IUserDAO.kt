package com.dedicated407.favoriteLiterature.Presentation.Room.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dedicated407.favoriteLiterature.Presentation.Room.Model.UserDTO

@Dao
interface IUserDAO {

    @Insert
    suspend fun registerUser(user: UserDTO)

    @Query("SELECT * FROM users WHERE login=(:login) AND password=(:password)")
    fun login(login: String, password: String): LiveData<UserDTO>
}