package com.dedicated407.favoriteLiterature.Presentation.Repository.Server.Models

import com.dedicated407.favoriteLiterature.Domain.Model.Role

class AuthUserResponse (
    var jwtToken: String,
    var userId: String,
    var role: Role
)