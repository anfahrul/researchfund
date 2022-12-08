package com.anfahrul.researchfund.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ResearchOfferRequest(
    @field:NotBlank
    val researchOfferName: String,

    @field:NotBlank
    val details: String,

    @field:NotBlank
    val category: String,

    @field:NotNull
    val proposalFunded: Int,

    @field:NotNull
    val fund: Int
)