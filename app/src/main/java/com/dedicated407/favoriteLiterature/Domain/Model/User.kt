package com.dedicated407.favoriteLiterature.Domain.Model

import java.util.*

open class User(
    open val id: String = UUID.randomUUID().toString(),
    open val login: String? = null,
    open val password: String? = null,
    open var name: String? = null,
    open var lastName: String? = null,
    open var patronymic: String? = null,
    open var role: Role = Role.User,
    open var phone: String? = null,
    open var email: String? = null,
    open val images: List<String>? = null
) {

    override fun toString(): String {
        return "$lastName $name $patronymic"
    }

    enum class Role {
        User,
        Writer,
        Editor,
        Moderator,
        Admin
    }
}
