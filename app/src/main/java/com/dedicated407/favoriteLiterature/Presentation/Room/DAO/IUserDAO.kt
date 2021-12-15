package com.dedicated407.favoriteLiterature.Presentation.Room.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dedicated407.favoriteLiterature.Presentation.Room.Model.UserDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface IUserDAO {

    @Insert
    suspend fun registerUser(user: UserDTO)

    @Query("SELECT * FROM users WHERE login=(:login)")
    fun login(login: String): Flow<UserDTO>
}