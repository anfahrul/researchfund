package com.anfahrul.researchfund.repository

import com.anfahrul.researchfund.entity.UserAccount
import com.anfahrul.researchfund.model.LoginResponse
import org.springframework.data.jpa.repository.JpaRepository

interface UserAccountRepository: JpaRepository<UserAccount, String> {
    fun findByEmail(email:String): UserAccount?
}