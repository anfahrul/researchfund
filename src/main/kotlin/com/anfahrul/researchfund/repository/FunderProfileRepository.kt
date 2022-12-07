package com.anfahrul.researchfund.repository

import com.anfahrul.researchfund.entity.FunderProfile
import com.anfahrul.researchfund.entity.ResearcherProfile
import org.springframework.data.jpa.repository.JpaRepository

interface FunderProfileRepository: JpaRepository<FunderProfile, Int> {

    fun findByUsername(username:String): FunderProfile?
}