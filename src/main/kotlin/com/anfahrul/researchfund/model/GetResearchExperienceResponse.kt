package com.anfahrul.researchfund.model

import com.anfahrul.researchfund.entity.ResearcherProfile

data class GetResearchExperienceResponse<T> (

    val researcherProfile: ResearcherProfile,

    val researchExperience: T
)
