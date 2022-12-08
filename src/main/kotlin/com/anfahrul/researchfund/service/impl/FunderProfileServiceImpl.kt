package com.anfahrul.researchfund.service.impl

import com.anfahrul.researchfund.entity.FunderProfile
import com.anfahrul.researchfund.exception.NotFoundException
import com.anfahrul.researchfund.model.UpdateFunderProfile
import com.anfahrul.researchfund.repository.FunderProfileRepository
import com.anfahrul.researchfund.service.FunderProfileService
import com.anfahrul.researchfund.validation.ValidationUtil
import org.springframework.data.repository.findByIdOrNull
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

    override fun get(funderId: String): FunderProfile {
        val funderProfile = funderProfileRepository.findByIdOrNull(funderId)
            ?: throw NotFoundException("Profil organisasi tidak ditemukan")

        return funderProfile
    }

    override fun update(researcherId: String, updateFunderProfile: UpdateFunderProfile): FunderProfile {
        validationUtil.validate(updateFunderProfile)

        val funderProfile = funderProfileRepository.findByIdOrNull(researcherId)
        if (funderProfile == null) {
            throw NotFoundException("Profil organisasi tidak ditemukan")
        }

        funderProfile.apply {
            organitationName = updateFunderProfile.organitationName
            address = updateFunderProfile.address
            organitation_description = updateFunderProfile.organitation_description
            phone = updateFunderProfile.phone
            email = updateFunderProfile.email
            logo = updateFunderProfile.logo
        }

        return funderProfileRepository.save(funderProfile)
    }
}