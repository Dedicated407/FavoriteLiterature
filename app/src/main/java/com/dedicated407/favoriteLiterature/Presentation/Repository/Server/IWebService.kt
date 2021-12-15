package com.dedicated407.favoriteLiterature.Presentation.Repository.Server

import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.Models.AuthUserRequest
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.Models.AuthUserResponse
import retrofit2.http.*

interface IWebService {
    @POST("/auth")
    suspend fun authenticationUser(@Body requestBody: AuthUserRequest): AuthUserResponse
}