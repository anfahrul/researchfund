package com.anfahrul.researchfund.repository

import com.anfahrul.researchfund.entity.Education
import com.anfahrul.researchfund.entity.ResearchOffer
import org.springframework.data.jpa.repository.JpaRepository

interface ResearchOfferRepository: JpaRepository<ResearchOffer, Int> {

    fun findByFunderId(funderId: String): List<ResearchOffer>
}