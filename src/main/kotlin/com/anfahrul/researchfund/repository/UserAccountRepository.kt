package com.anfahrul.researchfund.repository

import com.anfahrul.researchfund.entity.UserAccount
import org.springframework.data.jpa.repository.JpaRepository

interface UserAccountRepository: JpaRepository<UserAccount, String> {
}