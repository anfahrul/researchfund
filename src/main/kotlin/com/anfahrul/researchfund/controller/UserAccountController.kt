package com.anfahrul.researchfund.controller

import com.anfahrul.researchfund.model.*
import com.anfahrul.researchfund.service.FunderProfileService
import com.anfahrul.researchfund.service.ResearcherProfileService
import com.anfahrul.researchfund.service.UserAccountService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.bind.annotation.*

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
    fun login(@RequestBody body: LoginRequest): WebResponse<Any?> {

        val loginResponse = userAccountService.login(body)

        return WebResponse(
            code = 200,
            status = "login success",
            data = loginResponse
        )
    }

    @PostMapping("logout")
    fun logout(
        @RequestHeader("Authorization") authorization: String?
    ): WebResponse<Any?> {

        userAccountService.cookieCheck(authorization)

        return WebResponse(
            code = 200,
            status = "Logout success",
            data = null
        )
    }
}