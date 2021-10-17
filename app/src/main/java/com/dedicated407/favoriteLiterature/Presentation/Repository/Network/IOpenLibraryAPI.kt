package com.dedicated407.favoriteLiterature.Presentation.Repository.Network

import retrofit2.Call
import retrofit2.http.*


public interface IOpenLibraryAPI {

    @GET("search/?fields=author_name&")
    @Headers(
        "Accept: application/json"
    )
    fun getWritersByName(
        @Query("author") query: String
    ): Call<WriterResponse>

}