package com.dedicated407.favoriteLiterature.Presentation.Room.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dedicated407.favoriteLiterature.Domain.Model.Book
import com.google.gson.Gson


@Entity(tableName = "book", ignoredColumns = ["images"])
data class BookDTO(
    @PrimaryKey override val id: String,
    override val name: String?,
    override val authorName: String?,
    override val description: String?,
    val images_serialized: String
) : Book() {

    constructor(book: Book) : this(
        book.id,
        book.name,
        book.authorName,
        book.description,
        Gson().toJson(book.images)
    )


    override var images: List<String>? = null
        get() = Gson().fromJson<List<String>>(images_serialized, List::class.java)
}

