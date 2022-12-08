package com.anfahrul.researchfund.service.impl

import com.anfahrul.researchfund.entity.ResearcherProfile
import com.anfahrul.researchfund.exception.NotFoundException
import com.anfahrul.researchfund.model.UpdateResearcherProfile
import com.anfahrul.researchfund.repository.ResearcherProfileRepository
import com.anfahrul.researchfund.service.ResearcherProfileService
import com.anfahrul.researchfund.validation.ValidationUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ResearcherProfileServiceImpl(
    val researcherProfileRepository: ResearcherProfileRepository,
    val validationUtil: ValidationUtil
): ResearcherProfileService {
    override fun create(username: String) {
        validationUtil.validate(username)

        val researcherProfile = ResearcherProfile(
            username = username
        )

        researcherProfileRepository.save(researcherProfile)
    }

    override fun update(researcherId: String, updateResearcherProfile: UpdateResearcherProfile): ResearcherProfile {
        validationUtil.validate(updateResearcherProfile)

        val researcherProfile = researcherProfileRepository.findByIdOrNull(researcherId)
        if (researcherProfile == null) {
            throw NotFoundException("Profil peneliti tidak ditemukan")
        }

        researcherProfile.apply {
            name = updateResearcherProfile.name
            frontTitle = updateResearcherProfile.frontTitle
            backDegree = updateResearcherProfile.backDegree
            photo = updateResearcherProfile.photo
            ktpNo = updateResearcherProfile.ktpNo
            areasOfExperience = updateResearcherProfile.areasOfExperience
        }

        return researcherProfileRepository.save(researcherProfile)
    }

}