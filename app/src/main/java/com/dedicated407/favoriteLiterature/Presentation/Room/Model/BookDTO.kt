package com.dedicated407.favoriteLiterature.Presentation.Room.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dedicated407.favoriteLiterature.Domain.Model.Book
import com.dedicated407.favoriteLiterature.Domain.Model.User
import com.google.gson.Gson


@Entity(tableName = "book", ignoredColumns = ["author", "images"])
data class BookDTO(
    @PrimaryKey override val id: String,
    override val name: String?,
    val author_serialized: String,
    override val description: String?,
    val images_serialized: String
) : Book() {

    constructor(book: Book) : this(
        book.id,
        book.name,
        Gson().toJson(book.author),
        book.description,
        Gson().toJson(book.images)
    )

    override val author: User?
        get() = Gson().fromJson(author_serialized, User::class.java)

    override val images: List<String>?
        get() = Gson().fromJson<List<String>>(images_serialized, List::class.java)
}

