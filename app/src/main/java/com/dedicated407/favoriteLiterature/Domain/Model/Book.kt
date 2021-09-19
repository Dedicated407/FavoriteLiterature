package com.dedicated407.favoriteLiterature.Domain.Model

open class Book(open val name: String? = null, open val author: User? = null) {
    var description: String? = null
}
