package com.anfahrul.researchfund.model

import com.anfahrul.researchfund.entity.Role

data class LoginResponse(
    val username: String?,

    val email: String?,

    val role: Role?,

    val token: String?
)
