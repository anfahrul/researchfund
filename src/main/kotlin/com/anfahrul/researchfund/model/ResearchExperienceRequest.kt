package com.anfahrul.researchfund.model

import jakarta.persistence.Column
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ResearchExperienceRequest(
    @field:NotBlank
    val researchName: String,

    @field:NotNull
    val year: Int,

    @field:NotBlank
    val sourceOfFunding: String,

    @field:NotNull
    val roleName: String,
)