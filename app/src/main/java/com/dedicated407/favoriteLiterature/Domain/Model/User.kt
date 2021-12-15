package com.dedicated407.favoriteLiterature.Domain.Model

import java.util.*

open class User(
    open var id: String = UUID.randomUUID().toString(),
    open var login: String? = null,
    open var password: String? = null,
    open var jwtToken: String? = null,
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
}
