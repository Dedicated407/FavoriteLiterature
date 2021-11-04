package com.dedicated407.favoriteLiterature.Presentation.Room

import androidx.lifecycle.LiveData
import androidx.lifecycle.distinctUntilChanged
import com.dedicated407.favoriteLiterature.Domain.Model.User
import com.dedicated407.favoriteLiterature.Presentation.Repository.IUserRepository
import com.dedicated407.favoriteLiterature.Presentation.Room.DAO.IUserDAO
import com.dedicated407.favoriteLiterature.Presentation.Room.Model.UserDTO

class UserRepository(db: FavoriteLiteratureRoomDatabase) : IUserRepository {
    private val mUserDao: IUserDAO = db.userDao()

    override fun <T : User> loginUser(user: T): LiveData<User?>
        = mUserDao.login(user.login!!, user.password!!).distinctUntilChanged() as LiveData<User?>


    override suspend fun <T : User> registerUser(user: T)
            = mUserDao.registerUser(UserDTO(user))
}