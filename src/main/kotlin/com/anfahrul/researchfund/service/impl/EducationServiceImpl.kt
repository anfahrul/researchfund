package com.anfahrul.researchfund.service.impl

import com.anfahrul.researchfund.entity.Education
import com.anfahrul.researchfund.model.EducationRequest
import com.anfahrul.researchfund.repository.EducationRepository
import com.anfahrul.researchfund.service.EducationService
import com.anfahrul.researchfund.validation.ValidationUtil
import org.springframework.stereotype.Service

@Service
class EducationServiceImpl(
    val educationRepository: EducationRepository,
    val validationUtil: ValidationUtil
): EducationService {
    override fun add(researcherId: Int, educationRequest: EducationRequest): List<Education> {
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

    override fun getEducationByResearcherId(researcherId: Int): List<Education> {
        validationUtil.validate(researcherId)

        val educations = educationRepository.findByResearcherId(researcherId)

        return educations
    }
}