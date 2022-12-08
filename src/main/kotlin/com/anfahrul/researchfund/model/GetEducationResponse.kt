package com.anfahrul.researchfund.model

import com.anfahrul.researchfund.entity.ResearcherProfile

data class GetEducationResponse<T> (

    val researcherProfile: ResearcherProfile,

    val educations: T
)
