package com.dedicated407.favoriteLiterature.Presentation.Repository.Server.ServerRepository

import com.dedicated407.favoriteLiterature.DI.ServiceLocator
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.IWebService
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.Models.AuthUserRequest
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.Models.AuthUserResponse
import java.lang.Exception

class AuthUserRepository {
    private var mApiFavLit: IWebService = ServiceLocator.getInstance().getApiFavLit()

    suspend fun authenticationUser(login: String, password: String): AuthUserResponse? {
        return try {
            mApiFavLit.authenticationUser(AuthUserRequest(login, password))
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}