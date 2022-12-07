package com.anfahrul.researchfund.service

import com.anfahrul.researchfund.entity.UserAccount
import com.anfahrul.researchfund.model.CreateUserAccountRequest
import com.anfahrul.researchfund.model.CreateUserAccountResponse
import com.anfahrul.researchfund.model.LoginRequest
import com.anfahrul.researchfund.model.LoginResponse
import jakarta.servlet.http.HttpServletResponse

interface UserAccountService {

    fun create(createUserAccountRequest: CreateUserAccountRequest): CreateUserAccountResponse

    fun createFunderProfile(username: String)

    fun createResearcherProfile(username: String)

    fun login(loginRequest: LoginRequest, response: HttpServletResponse): LoginResponse?

    fun findByEmail(email: String): Boolean

    fun comparePassword(rawPassword: String, encodedPassword: String?): Boolean

    fun getUserByEmail(email: String): UserAccount?

    fun jwtConfiguration(userAccount: UserAccount?, response: HttpServletResponse): String

    fun cookieCheck(jwt: String?): Int

    fun deleteCookie(jwt: String?, response: HttpServletResponse)

}