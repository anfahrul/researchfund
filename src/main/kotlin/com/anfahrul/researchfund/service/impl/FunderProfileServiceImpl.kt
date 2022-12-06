package com.anfahrul.researchfund.service.impl

import com.anfahrul.researchfund.entity.FunderProfile
import com.anfahrul.researchfund.repository.FunderProfileRepository
import com.anfahrul.researchfund.service.FunderProfileService
import com.anfahrul.researchfund.validation.ValidationUtil
import org.springframework.stereotype.Service

@Service
class FunderProfileServiceImpl(
    val funderProfileRepository: FunderProfileRepository,
    val validationUtil: ValidationUtil
): FunderProfileService {
    override fun create(username: String) {
        validationUtil.validate(username)

        val funderProfile = FunderProfile(
            username = username
        )

        funderProfileRepository.save(funderProfile)
    }
}