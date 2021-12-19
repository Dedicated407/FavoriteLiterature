package com.dedicated407.favoriteLiterature.Presentation.Room

import com.dedicated407.favoriteLiterature.DI.ServiceLocator
import com.dedicated407.favoriteLiterature.Domain.Model.User
import com.dedicated407.favoriteLiterature.Presentation.Repository.IUserRepository
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.IWebService
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.Models.AuthUserRequest
import com.dedicated407.favoriteLiterature.Presentation.Room.DAO.IUserDAO
import com.dedicated407.favoriteLiterature.Presentation.Room.Model.UserDTO
import kotlinx.coroutines.flow.Flow

class UserRepository(
    db: FavoriteLiteratureRoomDatabase
) : IUserRepository {
    private var mApiFavLit: IWebService = ServiceLocator.getInstance().getApiFavLit()
    private val mUserDao: IUserDAO = db.userDao()

    suspend fun authenticationUser(login: String, password: String): Flow<User> {
        val response = mApiFavLit.authenticationUser(
            AuthUserRequest(login, password)
        )

        mUserDao.registerUser(
            UserDTO(User(
                id = response.userId,
                login = login,
                jwtToken = response.jwtToken,
                role = response.role
            ))
        )

        return mUserDao.login(login)
    }

    override suspend fun getUser(login: String?, password: String?): Flow<User?> {
        TODO("Not yet implemented")
    }

    override fun saveUser(user: User) {
        TODO("Not yet implemented")
    }
}