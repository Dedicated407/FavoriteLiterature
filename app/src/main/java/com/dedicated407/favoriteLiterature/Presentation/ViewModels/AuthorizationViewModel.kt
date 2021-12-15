package com.dedicated407.favoriteLiterature.Presentation.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.Models.AuthUserResponse
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.ServerRepository.AuthUserRepository
import kotlinx.coroutines.launch

class AuthorizationViewModel : ViewModel() {
    private val repository: AuthUserRepository = AuthUserRepository()
    public val mResponse: MutableLiveData<AuthUserResponse> = MutableLiveData()

    fun authenticationUser(login: String, password: String) {
        viewModelScope.launch {
            repository.authenticationUser(login, password)?.let {
                mResponse.value = AuthUserResponse(
                    it.jwtToken,
                    it.userId,
                    it.role
                )
            }
        }
    }
}