package com.anfahrul.researchfund.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class EducationRequest(
    @field:NotBlank
    val institutionName: String,

    @field:NotBlank
    val major: String,

    @field:NotNull
    val graduationYear: Int
)