package com.anfahrul.researchfund.repository

import com.anfahrul.researchfund.entity.FunderProfile
import org.springframework.data.jpa.repository.JpaRepository

interface FunderProfileRepository: JpaRepository<FunderProfile, Int> {
}