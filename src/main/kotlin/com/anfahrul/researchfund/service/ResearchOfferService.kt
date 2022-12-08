package com.anfahrul.researchfund.service

import com.anfahrul.researchfund.entity.ResearchOffer
import com.anfahrul.researchfund.model.ResearchOfferRequest
import com.anfahrul.researchfund.model.ResearchOfferResponse

interface ResearchOfferService {

    fun create(funderId: String, researchOfferRequest: ResearchOfferRequest): ResearchOfferResponse

    fun get(researchOfferId: Int): ResearchOffer

    fun getAllByFunderId(funderId: String): List<ResearchOffer>

    fun getAll(): List<ResearchOffer>

    fun update(researchOfferId: Int, researchOfferRequest: ResearchOfferRequest): ResearchOffer

    fun delete(researchOfferId: Int)
}