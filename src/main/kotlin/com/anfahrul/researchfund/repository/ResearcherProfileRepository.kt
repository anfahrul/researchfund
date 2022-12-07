package com.anfahrul.researchfund.repository

import com.anfahrul.researchfund.entity.ResearcherProfile
import com.anfahrul.researchfund.entity.UserAccount
import org.springframework.data.jpa.repository.JpaRepository

interface ResearcherProfileRepository: JpaRepository<ResearcherProfile, Int> {
    fun findByUsername(username:String): ResearcherProfile?
}