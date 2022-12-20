package com.anfahrul.researchfund.repository

import com.anfahrul.researchfund.entity.Proposal
import com.anfahrul.researchfund.model.GetProposalResponse
import org.springframework.data.jpa.repository.JpaRepository

interface proposalRepository: JpaRepository<Proposal, String> {
    fun findByResearchOfferId(researchOfferId: String): List<GetProposalResponse>
}