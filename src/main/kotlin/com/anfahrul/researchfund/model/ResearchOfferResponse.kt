package com.anfahrul.researchfund.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ResearchOfferResponse(

    val researchOfferName: String,

    val details: String,

    val category: String,

    val proposalFunded: Int,

    val fund: Int
)