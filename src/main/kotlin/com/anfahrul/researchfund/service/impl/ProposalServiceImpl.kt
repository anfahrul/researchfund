package com.anfahrul.researchfund.service.impl

import com.anfahrul.researchfund.entity.Proposal
import com.anfahrul.researchfund.entity.Review
import com.anfahrul.researchfund.exception.NotFoundException
import com.anfahrul.researchfund.model.GetProposalResponse
import com.anfahrul.researchfund.model.ProposalRequest
import com.anfahrul.researchfund.model.ProposalResponse
import com.anfahrul.researchfund.model.ReviewRequest
import com.anfahrul.researchfund.repository.ResearchOfferRepository
import com.anfahrul.researchfund.repository.ReviewRepository
import com.anfahrul.researchfund.repository.proposalRepository
import com.anfahrul.researchfund.service.ProposalService
import com.anfahrul.researchfund.validation.ValidationUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ProposalServiceImpl(
    val proposalRepository: proposalRepository,
    val researchOfferRepository: ResearchOfferRepository,
    val reviewRepository: ReviewRepository,
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

    override fun get(proposalId: String): GetProposalResponse {
        val proposal = proposalRepository.findByIdOrNull(proposalId) ?: throw NotFoundException("Proposal tidak ditemukan")

        return GetProposalResponse(
            proposal.proposalId,
            proposal.researchTitle,
            proposal.abstrac,
            proposal.keyword,
            proposal.filePath,
            proposal.researchOfferId,
            proposal.researcherId
        )
    }

    override fun findByResearchOfferId(researchOfferId: String): List<GetProposalResponse> {
        val researchOffer = researchOfferRepository.findByIdOrNull(researchOfferId)
            ?: throw NotFoundException("Research offer tidak ditemukan")

        return proposalRepository.findByResearchOfferId(researchOffer.researchOfferId)
    }

    override fun edit(proposalId: String, proposalRequest: ProposalRequest): Proposal {
        val proposal = proposalRepository.findByIdOrNull(proposalId) ?: throw NotFoundException("Proposal tidak ditemukan")

        proposal.apply {
            researchTitle = proposalRequest.researchTitle
            abstrac = proposalRequest.abstrac
            keyword = proposalRequest.keyword
        }

        return proposalRepository.save(proposal)
    }

    override fun review(proposalId: String, reviewRequest: ReviewRequest): String {
        validationUtil.validate(reviewRequest)

        val proposal = proposalRepository.findByIdOrNull(proposalId) ?: throw NotFoundException("Proposal tidak ditemukan")

        val review = Review(
            proposalId = proposalId,
            dateReviewed = System.currentTimeMillis().toString(),
            fundingStatus = reviewRequest.fundingStatus,
            message = reviewRequest.message
        )
        reviewRepository.save(review)

        return proposal.proposalId
    }
}