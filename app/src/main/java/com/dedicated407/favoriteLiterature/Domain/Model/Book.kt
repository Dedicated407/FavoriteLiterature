package com.dedicated407.favoriteLiterature.Domain.Model

import java.time.LocalDate

public class Book(var id: Int, var name: String, var author: User) {
    var description: String? = null
    var creationYear: LocalDate? = null
}