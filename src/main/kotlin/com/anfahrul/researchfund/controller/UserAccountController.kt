package com.anfahrul.researchfund.controller

import com.anfahrul.researchfund.entity.Role
import com.anfahrul.researchfund.model.*
import com.anfahrul.researchfund.service.FunderProfileService
import com.anfahrul.researchfund.service.ResearcherProfileService
import com.anfahrul.researchfund.service.UserAccountService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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

        val role = createAccountResponse.role
        if (role == Role.FUNDER) {
            funderProfileService.create(createAccountResponse.username)
        } else {
            researcherProfileService.create(createAccountResponse.username)
        }

        return WebResponse(
            code = 200,
            status = "Create user account success",
            data = createAccountResponse
        )
    }

    @PostMapping("login")
    fun login(@RequestBody body: LoginRequest, response: HttpServletResponse): WebResponse<Any?> {
        val userIsExist = userAccountService.findByEmail(body.email)
        if (!userIsExist) {
            return WebResponse(
                code = 400,
                status = "user account not registered",
                data = null
            )
        }

        val userAccount = userAccountService.getUserByEmail(body.email)
        val passwordIsSame = userAccountService.comparePassword(body.password, userAccount?.password)
        if (!passwordIsSame) {
            return WebResponse(
                code = 400,
                status = "email and password combination is invalid",
                data = null
            )
        }

        val issuer = userAccount?.username.toString()
        val key = "secret-key"
        val jwt = Jwts.builder()
            .setIssuer(issuer)
            .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000))
            .signWith(SignatureAlgorithm.HS512, key)
            .compact()

        val cookie = Cookie("jwt", jwt)
        cookie.isHttpOnly = true
        response.addCookie(cookie)

        val loginResponse = LoginResponse(
            username = userAccount?.username,
            email = userAccount?.email,
            role = userAccount?.role,
            token = jwt
        )

        return WebResponse(
            code = 200,
            status = "login success",
            data = loginResponse
        )
    }
}