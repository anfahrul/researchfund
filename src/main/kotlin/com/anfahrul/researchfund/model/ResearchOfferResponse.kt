package com.anfahrul.researchfund.model

import com.anfahrul.researchfund.entity.FunderProfile
import com.anfahrul.researchfund.entity.ResearchOffer
import com.anfahrul.researchfund.entity.ResearcherProfile
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ResearchOfferResponse(

    val funderProfile: FunderProfile?,

    val offer: ResearchOffer
)