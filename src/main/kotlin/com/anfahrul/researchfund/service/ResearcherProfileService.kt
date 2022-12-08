package com.anfahrul.researchfund.service

import com.anfahrul.researchfund.entity.ResearcherProfile
import com.anfahrul.researchfund.model.UpdateResearcherProfile

interface ResearcherProfileService {

    fun create(username: String)

    fun get(researcherId: String): ResearcherProfile

    fun update(researcherId: String, updateResearcherProfile: UpdateResearcherProfile): ResearcherProfile

}