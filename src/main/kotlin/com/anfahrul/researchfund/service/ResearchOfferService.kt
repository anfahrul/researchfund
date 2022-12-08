package com.anfahrul.researchfund.service

import com.anfahrul.researchfund.model.ResearchOfferRequest
import com.anfahrul.researchfund.model.ResearchOfferResponse

interface ResearchOfferService {

    fun create(funderId: Int, researchOfferRequest: ResearchOfferRequest): ResearchOfferResponse
}