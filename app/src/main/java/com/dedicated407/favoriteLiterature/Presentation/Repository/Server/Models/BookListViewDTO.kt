package com.dedicated407.favoriteLiterature.Presentation.Repository.Server.Models

import java.util.*

class BookListViewDTO (
    var id: UUID,
    var name: String,
    var authorName: String,
    var images: MutableList<String>
)