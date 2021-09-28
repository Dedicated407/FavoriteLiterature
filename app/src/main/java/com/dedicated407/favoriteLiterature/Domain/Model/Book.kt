package com.dedicated407.favoriteLiterature.Domain.Model

import java.util.*

open class Book(
    open val id: String = UUID.randomUUID().toString(),
    open val name: String? = null,
    open val author: User? = null,
    open val description: String? = null,
    open val images: List<String>? = null
)
