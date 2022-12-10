package com.anfahrul.researchfund.repository

import com.anfahrul.researchfund.entity.Proposal
import org.springframework.data.jpa.repository.JpaRepository

interface proposalRepository: JpaRepository<Proposal, String> {
}