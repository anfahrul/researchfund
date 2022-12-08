package com.anfahrul.researchfund.service

import com.anfahrul.researchfund.entity.ResearchExperience
import com.anfahrul.researchfund.model.GetResearchExperienceResponse
import com.anfahrul.researchfund.model.ResearchExperienceRequest

interface ResearchExperienceService {
    fun add(researcherId: String, researchExperienceRequest: ResearchExperienceRequest): GetResearchExperienceResponse<Any>

    fun getResearchExperienceByResearcherId(researcherId: String): GetResearchExperienceResponse<Any>
}