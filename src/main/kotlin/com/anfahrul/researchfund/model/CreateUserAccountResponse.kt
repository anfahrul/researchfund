package com.anfahrul.researchfund.model

import com.anfahrul.researchfund.entity.Role

data class CreateUserAccountResponse(
    val username: String,

    val password: String,

    val email: String,

    val role: Role
)
