package com.anfahrul.researchfund.controller

import com.anfahrul.researchfund.entity.Role
import com.anfahrul.researchfund.exception.BadRequestException
import com.anfahrul.researchfund.exception.UnauthorizedException
import com.anfahrul.researchfund.model.*
import com.anfahrul.researchfund.service.FunderProfileService
import com.anfahrul.researchfund.service.ResearcherProfileService
import com.anfahrul.researchfund.service.UserAccountService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.HttpClientErrorException.Unauthorized
import java.util.*

@RestController
@RequestMapping("api")
class UserAccountController(
    val userAccountService: UserAccountService,
    val funderProfileService: FunderProfileService,
    val researcherProfileService: ResearcherProfileService
    ) {
    @PostMapping("register")
    fun createUserAccount(@RequestBody body: CreateUserAccountRequest): WebResponse<CreateUserAccountResponse> {
        val createAccountResponse = userAccountService.create(body)

        return WebResponse(
            code = 200,
            status = "Create user account success",
            data = createAccountResponse
        )
    }

    @PostMapping("login")
    fun login(@RequestBody body: LoginRequest, response: HttpServletResponse): WebResponse<Any?> {

        val loginResponse = userAccountService.login(body, response)

        return WebResponse(
            code = 200,
            status = "login success",
            data = loginResponse
        )
    }

    @PostMapping("logout")
    fun logout(@CookieValue("jwt") jwt: String?, response: HttpServletResponse): WebResponse<Any?> {

        userAccountService.deleteCookie(jwt, response)

        return WebResponse(
            code = 200,
            status = "Logout success",
            data = null
        )
    }
}