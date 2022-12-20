package com.anfahrul.researchfund.model

import com.anfahrul.researchfund.entity.FundingStatus
import jakarta.persistence.Column
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ReviewResponse(

    var reviewId: String,

    var proposalId: String,

    var dateReviewed: String,

    var fundingStatus: FundingStatus,

    var message: String,
)