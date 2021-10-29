package com.dedicated407.favoriteLiterature.Presentation.Repository;

import androidx.lifecycle.LiveData
import com.dedicated407.favoriteLiterature.Domain.Model.User

public interface IUserRepository {
    suspend fun <T : User> registerUser(user: T)
    fun <T: User> loginUser(user: T): LiveData<User?>
}
