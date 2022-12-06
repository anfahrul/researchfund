package com.anfahrul.researchfund.model

import com.anfahrul.researchfund.entity.Role
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateUserAccountRequest(
    @field:NotBlank
    val username: String,

    @field:NotBlank
    val password: String,

    @field:NotBlank
    val email: String,

    @field:NotNull
    val role: Role
)
