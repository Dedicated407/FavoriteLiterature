package com.dedicated407.favoriteLiterature.Presentation.Room.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dedicated407.favoriteLiterature.Domain.Model.User
import com.google.gson.Gson

@Entity(tableName = "users", ignoredColumns = ["images"])
data class UserDTO(
    @PrimaryKey override val id: String,
    override val login: String?,
    override val password: String?,
    override var name: String?,
    override var lastName: String?,
    override var patronymic: String?,
    override var role: Role = Role.User,
    override var phone: String?,
    override var email: String?,
    val images_serialized: String
    ) : User() {

    constructor(user: User) : this(
        user.id,
        user.login,
        user.password,
        user.name,
        user.lastName,
        user.patronymic,
        user.role,
        user.phone,
        user.email,
        Gson().toJson(user.images)
    )

    override val images: List<String>?
        get() = Gson().fromJson<List<String>>(images_serialized, List::class.java)
}