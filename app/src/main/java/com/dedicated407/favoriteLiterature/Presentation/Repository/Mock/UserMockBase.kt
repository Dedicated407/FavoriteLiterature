package com.dedicated407.favoriteLiterature.Presentation.Repository.Mock

import androidx.lifecycle.MutableLiveData
import com.dedicated407.favoriteLiterature.Domain.Model.User
import com.dedicated407.favoriteLiterature.Presentation.Repository.IUserRepository
import kotlinx.coroutines.flow.Flow

class UserMockBase : IUserRepository {
    private var data: MutableLiveData<List<User>>
    private var list: ArrayList<User> = arrayListOf()

    init {
        val user1 = User(
                name = "Ilya",
                lastName = "Tsypin",
                patronymic = "Pavlovich",
                phone = "+7 (800) 555-35-35",
                email = "ilyaboy@list.ru"
            )
        list.add(user1)

        data = MutableLiveData(list)
    }

    override suspend fun getUser(login: String?, password: String?): Flow<User?> {
        TODO("Not yet implemented")
    }

    override fun saveUser(user: User) {
        TODO("Not yet implemented")
    }
}