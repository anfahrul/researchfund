package com.anfahrul.researchfund.service

import com.anfahrul.researchfund.entity.Education
import com.anfahrul.researchfund.entity.Proposal
import com.anfahrul.researchfund.model.*

interface ProposalService {
    fun create(researcherId: String, researchOfferId: String, proposalRequest: ProposalRequest): ProposalResponse

    fun get(proposalId: String): GetProposalResponse

    fun findByResearchOfferId(researchOfferId: String): List<GetProposalResponse>

    fun edit(proposalId: String, proposalRequest: ProposalRequest): Proposal

    fun review(proposalId: String, reviewRequest: ReviewRequest): String
}