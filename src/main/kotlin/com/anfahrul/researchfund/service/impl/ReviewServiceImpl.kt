package com.anfahrul.researchfund.service.impl

import com.anfahrul.researchfund.entity.Proposal
import com.anfahrul.researchfund.entity.Review
import com.anfahrul.researchfund.exception.NotFoundException
import com.anfahrul.researchfund.model.*
import com.anfahrul.researchfund.repository.ResearchOfferRepository
import com.anfahrul.researchfund.repository.ReviewRepository
import com.anfahrul.researchfund.repository.proposalRepository
import com.anfahrul.researchfund.service.ProposalService
import com.anfahrul.researchfund.service.ReviewService
import com.anfahrul.researchfund.validation.ValidationUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ReviewServiceImpl(
    val proposalRepository: proposalRepository,
    val reviewRepository: ReviewRepository,
    val validationUtil: ValidationUtil
): ReviewService {
    override fun get(reviewId: String): ReviewResponse {
        val review = reviewRepository.findByIdOrNull(reviewId) ?: throw NotFoundException("Review tidak ditemukan")

        return ReviewResponse(
            reviewId = review.reviewId,
            proposalId = review.proposalId,
            dateReviewed = review.dateReviewed,
            fundingStatus = review.fundingStatus,
            message = review.message
        )
    }

    override fun findByProposalId(proposalId: String): List<ReviewResponse> {
        val proposal = proposalRepository.findByIdOrNull(proposalId) ?: throw NotFoundException("Proposal tidak ditemukan")

        return reviewRepository.findByProposalId(proposal.proposalId)
    }

}