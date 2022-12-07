package com.anfahrul.researchfund.service

import com.anfahrul.researchfund.entity.ResearchExperience
import com.anfahrul.researchfund.model.ResearchExperienceRequest

interface ResearchExperienceService {
    fun add(researcherId: Int, researchExperienceRequest: ResearchExperienceRequest): List<ResearchExperience>

    fun getResearchExperienceByResearcherId(researcherId: Int): List<ResearchExperience>
}