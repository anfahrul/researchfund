package com.anfahrul.researchfund.service.impl

import com.anfahrul.researchfund.entity.Proposal
import com.anfahrul.researchfund.model.ProposalRequest
import com.anfahrul.researchfund.model.ProposalResponse
import com.anfahrul.researchfund.repository.proposalRepository
import com.anfahrul.researchfund.service.ProposalService
import com.anfahrul.researchfund.validation.ValidationUtil
import org.springframework.stereotype.Service

@Service
class ProposalServiceImpl(
    val proposalRepository: proposalRepository,
    val validationUtil: ValidationUtil
): ProposalService {
    override fun create(
        researcherId: String,
        researchOfferId: String,
        proposalRequest: ProposalRequest
    ): ProposalResponse {
        validationUtil.validate(proposalRequest)

        val proposal = Proposal(
            researchTitle = proposalRequest.researchTitle,
            abstrac = proposalRequest.abstrac,
            keyword = proposalRequest.keyword,
            researchOfferId = researchOfferId,
            researcherId = researcherId
        )

        proposalRepository.save(proposal)

        return ProposalResponse(
            researchTitle = proposal.researchTitle,
            abstrac = proposal.abstrac,
            keyword = proposal.keyword,
            researchOfferId = proposal.researchOfferId,
            researcherId = proposal.researcherId,
        )
    }
}