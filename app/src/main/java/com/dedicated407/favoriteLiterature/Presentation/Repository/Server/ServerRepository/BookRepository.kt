package com.dedicated407.favoriteLiterature.Presentation.Repository.Server.ServerRepository

import com.dedicated407.favoriteLiterature.DI.ServiceLocator
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.IWebService
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.Models.BookListViewDTO

class BookRepository {
    private var mApiFavLit: IWebService = ServiceLocator.getInstance().getApiFavLit()

    suspend fun getBook(bookId: String) =
        mApiFavLit.getBook(bookId)

    suspend fun getAllBooks(): List<BookListViewDTO> =
        mApiFavLit.getAllBooks()

}