package com.dedicated407.favoriteLiterature.Presentation.Repository.Server

import com.dedicated407.favoriteLiterature.Domain.Model.Book
import com.dedicated407.favoriteLiterature.Domain.Model.User
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.Models.AuthUserRequest
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.Models.AuthUserResponse
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.Models.BookListViewDTO
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.Models.BookRequest
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.*

interface IWebService {
    @POST("/auth")
    suspend fun authenticationUser(@Body requestBody: AuthUserRequest): AuthUserResponse

    @GET("/user/findById")
    suspend fun findUserById(@Path("id") id: String): User

    @GET("/book/all")
    suspend fun getAllBooks(): List<BookListViewDTO>

    @GET("/book/{id}")
    suspend fun getBook(@Path("id") id: String): Book

    @POST("/book/add")
    suspend fun addBook(@Body requestBody: BookRequest): String?

    @Multipart
    @POST("/book/add/{id}/image")
    suspend fun addImage(
        @Path("id") id: String,
        @Part filePart: MultipartBody.Part
    )

    @Streaming
    @GET("/book/image/{id}")
    suspend fun downloadImage(@Path("id") id: String): ResponseBody?
}