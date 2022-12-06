package com.anfahrul.researchfund.repository

import com.anfahrul.researchfund.entity.ResearcherProfile
import org.springframework.data.jpa.repository.JpaRepository

interface ResearcherProfileRepository: JpaRepository<ResearcherProfile, Int> {
}