package com.dedicated407.favoriteLiterature.Presentation.Room.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import org.jetbrains.annotations.NotNull
import androidx.room.PrimaryKey
import com.dedicated407.favoriteLiterature.Domain.Model.Book
import com.dedicated407.favoriteLiterature.Domain.Model.User
import com.google.gson.Gson


@Entity(tableName = "book", ignoredColumns = ["author"])
open class BookDTO(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo
    val id: Int,
    @ColumnInfo
    override val name: String?,
    @ColumnInfo
    var author_serialized: String
    ) : Book() {

    constructor(book: Book) : this(
        0,
        book.name,
        Gson().toJson(book.author)
    )

    override val author: User
        get() = Gson().fromJson(author_serialized, User::class.java)
}

