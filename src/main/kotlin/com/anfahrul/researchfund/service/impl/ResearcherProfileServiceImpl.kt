package com.anfahrul.researchfund.service.impl

import com.anfahrul.researchfund.entity.ResearcherProfile
import com.anfahrul.researchfund.repository.ResearcherProfileRepository
import com.anfahrul.researchfund.service.ResearcherProfileService
import com.anfahrul.researchfund.validation.ValidationUtil
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

}