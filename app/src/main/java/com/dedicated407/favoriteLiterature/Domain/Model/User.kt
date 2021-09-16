package com.dedicated407.favoriteLiterature.Domain.Model

import java.time.LocalDate
import java.time.LocalDateTime

public class User(var id: Int, var name: String, var lastName: String) {
    var patronymic: String? = null
    var phone: String? = null
    var email: String? = null
    var dateOfBirth: LocalDate? = null
    var dateOfDeath: LocalDate? = null
    var lastVisit: LocalDateTime? = null
}


//data class User(
//    @field:PrimaryKey(autoGenerate = true)
//    @field:NotNull
//    @ColumnInfo
//    val userId: Int,
//
//    val name: String,
//
//    val role: String,
//
//    val lastTimeVisit: String
//) { }
