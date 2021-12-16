package com.dedicated407.favoriteLiterature.Presentation.Repository.Server

import com.dedicated407.favoriteLiterature.Domain.Model.Book
import com.dedicated407.favoriteLiterature.Domain.Model.User
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.Models.AuthUserRequest
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.Models.AuthUserResponse
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.Models.BookListViewDTO
import retrofit2.http.*

interface IWebService {
    @POST("/auth")
    suspend fun authenticationUser(@Body requestBody: AuthUserRequest): AuthUserResponse

    @GET("/user/findById")
    suspend fun findUserById(@Path("id") id: String): User

    @GET("/book/all")
    suspend fun getAllBooks(): List<BookListViewDTO>

    @GET("/book/findById")
    suspend fun getBook(@Path("id") id: String): Book
}