package com.dedicated407.favoriteLiterature.Presentation.Repository.Server.ServerRepository

import androidx.core.net.toUri
import com.dedicated407.favoriteLiterature.DI.ServiceLocator
import com.dedicated407.favoriteLiterature.Domain.Model.Book
import com.dedicated407.favoriteLiterature.Presentation.Repository.ImageLoader
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.IWebService
import com.dedicated407.favoriteLiterature.Presentation.Repository.Server.Models.BookListViewDTO
import java.lang.Exception

class BookRepository(
    private val imageLoader: ImageLoader
) {
    private var mApiFavLit: IWebService = ServiceLocator.getInstance().getApiFavLit()


    suspend fun getBook(bookId: String): Book =
        mApiFavLit.getBook(bookId)

    suspend fun getAllBooks(): List<BookListViewDTO> =
        mApiFavLit.getAllBooks()

    suspend fun addBook(book: Book) {
        try {
            val id = mApiFavLit.addBook(book)
            book.images?.map {
                mApiFavLit.addImage(
                    id.toString(),
                    imageLoader.fromUri(it.toUri())
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    suspend fun downloadImage(id: String)
        = try {
            imageLoader.fromResponse(
                mApiFavLit.downloadImage(
                    id
                ), id
            ) } catch (e: Throwable) {
                e.printStackTrace()
                id
            }
}