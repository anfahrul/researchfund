package com.anfahrul.researchfund.repository

import com.anfahrul.researchfund.entity.Review
import com.anfahrul.researchfund.model.ReviewResponse
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository: JpaRepository<Review, String> {
    fun findByProposalId(proposalId: String): List<ReviewResponse>
}