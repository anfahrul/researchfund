package com.anfahrul.researchfund.service

import com.anfahrul.researchfund.entity.Education
import com.anfahrul.researchfund.model.EducationRequest
import com.anfahrul.researchfund.model.GetEducationResponse
import com.anfahrul.researchfund.model.ProposalRequest
import com.anfahrul.researchfund.model.ProposalResponse

interface ProposalService {
    fun create(researcherId: String, researchOfferId: String, proposalRequest: ProposalRequest): ProposalResponse
}