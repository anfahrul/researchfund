package com.anfahrul.researchfund.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ProposalRequest(
    @field:NotBlank
    val researchTitle: String,

    @field:NotBlank
    val abstrac: String,

    @field:NotBlank
    val keyword: String,

    val filePath: String
)