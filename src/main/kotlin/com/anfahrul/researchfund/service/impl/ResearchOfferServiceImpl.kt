package com.anfahrul.researchfund.service.impl

import com.anfahrul.researchfund.entity.ResearchOffer
import com.anfahrul.researchfund.exception.NotFoundException
import com.anfahrul.researchfund.model.ResearchOfferRequest
import com.anfahrul.researchfund.model.ResearchOfferResponse
import com.anfahrul.researchfund.repository.FunderProfileRepository
import com.anfahrul.researchfund.repository.ResearchOfferRepository
import com.anfahrul.researchfund.service.ResearchOfferService
import com.anfahrul.researchfund.validation.ValidationUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ResearchOfferServiceImpl(
    val researchOfferRepository: ResearchOfferRepository,
    val funderProfileRepository: FunderProfileRepository,
    val validationUtil: ValidationUtil
): ResearchOfferService {
    override fun create(funderId: String, researchOfferRequest: ResearchOfferRequest): ResearchOfferResponse {
        validationUtil.validate(researchOfferRequest)

        val researchOffer = ResearchOffer(
            researchOfferName = researchOfferRequest.researchOfferName,
            details = researchOfferRequest.details,
            category = researchOfferRequest.category,
            proposalFunded = researchOfferRequest.proposalFunded,
            fund = researchOfferRequest.fund,
            funderId = funderId
        )

        researchOfferRepository.save(researchOffer)

        return ResearchOfferResponse(
            researchOfferName = researchOffer.researchOfferName,
            details = researchOffer.details,
            category = researchOffer.category,
            proposalFunded = researchOffer.proposalFunded,
            fund = researchOffer.fund,
        )
    }

    override fun get(researchOfferId: String): ResearchOffer {
        val researchOffer = researchOfferRepository.findByIdOrNull(researchOfferId)
            ?: throw NotFoundException("Research offer tidak ditemukan")

        return researchOffer
    }

    override fun getAllByFunderId(funderId: String): List<ResearchOffer> {
        val researchOffer = funderProfileRepository.findByIdOrNull(funderId)
            ?: throw NotFoundException("Profile organisasi tidak ditemukan")

        return researchOfferRepository.findByFunderId(funderId)
    }

    override fun getAll(): List<ResearchOffer> {
        return researchOfferRepository.findAll()
    }

    override fun update(researchOfferId: String, researchOfferRequest: ResearchOfferRequest): ResearchOffer {
        validationUtil.validate(researchOfferRequest)

        val researchOffer = researchOfferRepository.findByIdOrNull(researchOfferId)
            ?: throw NotFoundException("Research offer tidak ditemukan")

        researchOffer.apply {
            researchOfferName = researchOfferRequest.researchOfferName
            details = researchOfferRequest.details
            category = researchOfferRequest.category
            proposalFunded = researchOfferRequest.proposalFunded
            fund = researchOfferRequest.fund
        }

        return researchOfferRepository.save(researchOffer)
    }

    override fun delete(researchOfferId: String) {
        val researchOffer = researchOfferRepository.findByIdOrNull(researchOfferId)
            ?: throw NotFoundException("Research offer tidak ditemukan")

        researchOfferRepository.deleteById(researchOffer.researchOfferId)
    }
}