package com.anfahrul.researchfund.repository

import com.anfahrul.researchfund.entity.Education
import org.springframework.data.jpa.repository.JpaRepository

interface EducationRepository: JpaRepository<Education, Int> {
    fun findByResearcherId(researcherId: Int): List<Education>
}