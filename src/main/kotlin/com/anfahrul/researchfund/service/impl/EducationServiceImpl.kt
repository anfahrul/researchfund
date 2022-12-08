package com.anfahrul.researchfund.service.impl

import com.anfahrul.researchfund.entity.Education
import com.anfahrul.researchfund.exception.NotFoundException
import com.anfahrul.researchfund.model.EducationRequest
import com.anfahrul.researchfund.model.GetEducationResponse
import com.anfahrul.researchfund.repository.EducationRepository
import com.anfahrul.researchfund.repository.ResearcherProfileRepository
import com.anfahrul.researchfund.service.EducationService
import com.anfahrul.researchfund.validation.ValidationUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class EducationServiceImpl(
    val educationRepository: EducationRepository,
    val researcherProfileRepository: ResearcherProfileRepository,
    val validationUtil: ValidationUtil
): EducationService {
    override fun add(researcherId: String, educationRequest: EducationRequest): GetEducationResponse<Any> {
        validationUtil.validate(researcherId)
        validationUtil.validate(educationRequest)

        val education = Education(
            institutionName = educationRequest.institutionName,
            major = educationRequest.major,
            graduationYear = educationRequest.graduationYear,
            researcherId = researcherId
        )

        educationRepository.save(education)

        val educations = getEducationByResearcherId(researcherId)
        return educations
    }

    override fun getEducationByResearcherId(researcherId: String): GetEducationResponse<Any> {
        validationUtil.validate(researcherId)

        val researcherProfile = researcherProfileRepository.findByIdOrNull(researcherId)
        if (researcherProfile == null) {
            throw NotFoundException("Profil tidak ditemukan")
        }

        val educations = educationRepository.findByResearcherId(researcherId)

        return GetEducationResponse(
            researcherProfile = researcherProfile,
            educations = educations
        )
    }
}