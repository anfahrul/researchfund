package com.anfahrul.researchfund.service.impl

import com.anfahrul.researchfund.entity.ResearchExperience
import com.anfahrul.researchfund.exception.NotFoundException
import com.anfahrul.researchfund.model.GetResearchExperienceResponse
import com.anfahrul.researchfund.model.ResearchExperienceRequest
import com.anfahrul.researchfund.repository.ResearchExperienceRepository
import com.anfahrul.researchfund.repository.ResearcherProfileRepository
import com.anfahrul.researchfund.service.ResearchExperienceService
import com.anfahrul.researchfund.validation.ValidationUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ResearchExperienceServiceImpl(
    val researchExperienceRepository: ResearchExperienceRepository,
    val researcherProfileRepository: ResearcherProfileRepository,
    val validationUtil: ValidationUtil
): ResearchExperienceService {
    override fun add(
        researcherId: String,
        researchExperienceRequest: ResearchExperienceRequest
    ): GetResearchExperienceResponse<Any> {
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

    override fun getResearchExperienceByResearcherId(researcherId: String): GetResearchExperienceResponse<Any> {
        validationUtil.validate(researcherId)

        val researcherProfile = researcherProfileRepository.findByIdOrNull(researcherId)
        if (researcherProfile == null) {
            throw NotFoundException("Profil tidak ditemukan")
        }

        val researchExperience = researchExperienceRepository.findByResearcherId(researcherId)

        return GetResearchExperienceResponse(
            researcherProfile = researcherProfile,
            researchExperience = researchExperience
        )
    }
}