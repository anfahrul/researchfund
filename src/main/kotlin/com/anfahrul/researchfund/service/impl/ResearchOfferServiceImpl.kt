package com.anfahrul.researchfund.service.impl

import com.anfahrul.researchfund.entity.ResearchOffer
import com.anfahrul.researchfund.model.ResearchOfferRequest
import com.anfahrul.researchfund.model.ResearchOfferResponse
import com.anfahrul.researchfund.repository.ResearchOfferRepository
import com.anfahrul.researchfund.service.ResearchOfferService
import com.anfahrul.researchfund.validation.ValidationUtil
import org.springframework.stereotype.Service

@Service
class ResearchOfferServiceImpl(
    val researchOfferRepository: ResearchOfferRepository,
    val validationUtil: ValidationUtil
): ResearchOfferService {
    override fun create(funderId: Int, researchOfferRequest: ResearchOfferRequest): ResearchOfferResponse {
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
}