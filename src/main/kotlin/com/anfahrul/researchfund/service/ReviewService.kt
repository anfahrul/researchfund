package com.anfahrul.researchfund.service

import com.anfahrul.researchfund.entity.Education
import com.anfahrul.researchfund.entity.Proposal
import com.anfahrul.researchfund.model.*

interface ReviewService {

    fun get(reviewId: String): ReviewResponse

    fun findByProposalId(proposalId: String): List<ReviewResponse>
}