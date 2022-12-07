package com.anfahrul.researchfund.repository

import com.anfahrul.researchfund.entity.ResearchExperience
import org.springframework.data.jpa.repository.JpaRepository

interface ResearchExperienceRepository: JpaRepository<ResearchExperience, Int> {

    fun findByResearcherId(researcherId: Int): List<ResearchExperience>
}