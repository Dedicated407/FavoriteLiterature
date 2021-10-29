package com.dedicated407.favoriteLiterature.Presentation.Repository.Mock

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dedicated407.favoriteLiterature.Domain.Model.User
import com.dedicated407.favoriteLiterature.Presentation.Repository.IUserRepository

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
    override suspend fun <T : User> registerUser(user: T) {
        list.add(user)

        data = MutableLiveData(list)
    }

    override fun <T : User> loginUser(user: T): LiveData<User?> {
        TODO("Not yet implemented")
    }
}