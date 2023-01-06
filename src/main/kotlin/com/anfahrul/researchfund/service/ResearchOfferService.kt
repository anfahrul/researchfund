package com.anfahrul.researchfund.service

import com.anfahrul.researchfund.entity.ResearchOffer
import com.anfahrul.researchfund.model.ResearchOfferRequest
import com.anfahrul.researchfund.model.ResearchOfferResponse

interface ResearchOfferService {

    fun create(funderId: String, researchOfferRequest: ResearchOfferRequest): ResearchOfferResponse

    fun get(researchOfferId: String): ResearchOfferResponse

    fun getAllByFunderId(funderId: String): List<ResearchOffer>

    fun getAll(): List<ResearchOffer>

    fun update(researchOfferId: String, researchOfferRequest: ResearchOfferRequest): ResearchOffer

    fun delete(researchOfferId: String)
}