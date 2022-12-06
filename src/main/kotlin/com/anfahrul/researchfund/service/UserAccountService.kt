package com.anfahrul.researchfund.service

import com.anfahrul.researchfund.entity.UserAccount
import com.anfahrul.researchfund.model.CreateUserAccountRequest
import com.anfahrul.researchfund.model.CreateUserAccountResponse
import com.anfahrul.researchfund.model.LoginRequest
import com.anfahrul.researchfund.model.LoginResponse

interface UserAccountService {

    fun create(createUserAccountRequest: CreateUserAccountRequest): CreateUserAccountResponse

    fun findByEmail(email: String): Boolean

    fun comparePassword(rawPassword: String, encodedPassword: String?): Boolean

    fun getUserByEmail(email: String): UserAccount?

}