package com.dedicated407.favoriteLiterature.Presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dedicated407.favoriteLiterature.Domain.Model.User
import com.dedicated407.favoriteLiterature.Presentation.Repository.Repository

class AuthorizationViewModel() : ViewModel() {

    fun authenticationUser(login: String, password: String) : LiveData<User?>
        = Repository.getUserRepository().loginUser(
            User(
                login = login,
                password = password
            )
        )
}