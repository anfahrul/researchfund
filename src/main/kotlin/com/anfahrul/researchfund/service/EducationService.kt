package com.anfahrul.researchfund.service

import com.anfahrul.researchfund.entity.Education
import com.anfahrul.researchfund.model.EducationRequest

interface EducationService {
    fun add(researcherId: Int, educationRequest: EducationRequest): List<Education>

    fun getEducationByResearcherId(researcherId: Int): List<Education>
}