package com.dedicated407.favoriteLiterature.Domain.Model

class User(
    var name: String,
    var lastName: String,
    var patronymic: String? = null,
    var phone: String? = null,
    var email: String? = null,
    open val images: List<String>? = null
) {
    override fun toString(): String {
        return "$lastName $name"
    }
}
