package com.anfahrul.researchfund.model

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ProposalResponse(
    val researchTitle: String,

    val abstrac: String,

    val keyword: String,

    val filePath: String,

    val researchOfferId: String,

    val researcherId: String
)