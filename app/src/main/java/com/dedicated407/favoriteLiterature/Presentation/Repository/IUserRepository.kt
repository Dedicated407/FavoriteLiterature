package com.dedicated407.favoriteLiterature.Presentation.Repository

import kotlinx.coroutines.flow.Flow
import com.dedicated407.favoriteLiterature.Domain.Model.User

interface IUserRepository {
    suspend fun getUser(login: String? = null, password: String? = null): Flow<User?>
    fun saveUser(user: User)
}
