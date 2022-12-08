package com.anfahrul.researchfund.service

import com.anfahrul.researchfund.entity.Education
import com.anfahrul.researchfund.model.EducationRequest
import com.anfahrul.researchfund.model.GetEducationResponse

interface EducationService {
    fun add(researcherId: String, educationRequest: EducationRequest): GetEducationResponse<Any>

    fun getEducationByResearcherId(researcherId: String): GetEducationResponse<Any>
}