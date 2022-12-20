package com.anfahrul.researchfund.model

import com.anfahrul.researchfund.entity.FundingStatus
import jakarta.persistence.Column
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ReviewRequest(

    @field:NotNull
    var fundingStatus: FundingStatus,

    @field:NotBlank
    var message: String,
)