package com.anfahrul.researchfund.service.impl

import com.anfahrul.researchfund.entity.ResearchExperience
import com.anfahrul.researchfund.model.ResearchExperienceRequest
import com.anfahrul.researchfund.repository.ResearchExperienceRepository
import com.anfahrul.researchfund.service.ResearchExperienceService
import com.anfahrul.researchfund.validation.ValidationUtil
import org.springframework.stereotype.Service

@Service
class ResearchExperienceServiceImpl(
    val researchExperienceRepository: ResearchExperienceRepository,
    val validationUtil: ValidationUtil
): ResearchExperienceService {
    override fun add(
        researcherId: Int,
        researchExperienceRequest: ResearchExperienceRequest
    ): List<ResearchExperience> {
        validationUtil.validate(researcherId)
        validationUtil.validate(researchExperienceRequest)

        val researchExperience = ResearchExperience(
            researchName = researchExperienceRequest.researchName,
            year = researchExperienceRequest.year,
            sourceOfFunding = researchExperienceRequest.sourceOfFunding,
            roleName = researchExperienceRequest.roleName,
            researcherId = researcherId
        )

        researchExperienceRepository.save(researchExperience)

        val researchExperiences = getResearchExperienceByResearcherId(researcherId)
        return researchExperiences
    }

    override fun getResearchExperienceByResearcherId(researcherId: Int): List<ResearchExperience> {
        validationUtil.validate(researcherId)

        val researchExperience = researchExperienceRepository.findByResearcherId(researcherId)

        return researchExperience
    }
}