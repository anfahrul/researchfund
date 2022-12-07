package com.anfahrul.researchfund.service.impl

import com.anfahrul.researchfund.entity.FunderProfile
import com.anfahrul.researchfund.entity.ResearcherProfile
import com.anfahrul.researchfund.entity.Role
import com.anfahrul.researchfund.entity.UserAccount
import com.anfahrul.researchfund.exception.BadRequestException
import com.anfahrul.researchfund.exception.UnauthorizedException
import com.anfahrul.researchfund.model.*
import com.anfahrul.researchfund.repository.FunderProfileRepository
import com.anfahrul.researchfund.repository.ResearcherProfileRepository
import com.anfahrul.researchfund.repository.UserAccountRepository
import com.anfahrul.researchfund.service.UserAccountService
import com.anfahrul.researchfund.validation.ValidationUtil
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserAccountServiceImpl(
    val userAccountRepository: UserAccountRepository,
    val funderProfileRepository: FunderProfileRepository,
    val researcherProfileRepository: ResearcherProfileRepository,
    val validationUtil: ValidationUtil
    ) : UserAccountService {

    private val passwordEncoder = BCryptPasswordEncoder()

    override fun create(createUserAccountRequest: CreateUserAccountRequest): CreateUserAccountResponse {
        validationUtil.validate(createUserAccountRequest)

        val userAccount = UserAccount(
            username = createUserAccountRequest.username,
            password = this.passwordEncoder.encode(createUserAccountRequest.password),
            email = createUserAccountRequest.email,
            role = createUserAccountRequest.role
        )

        userAccountRepository.save(userAccount)

        if (createUserAccountRequest.role == Role.FUNDER) {
            createFunderProfile(userAccount.username)
        } else {
            createResearcherProfile(userAccount.username)
        }

        return CreateUserAccountResponse(
            username = userAccount.username,
            password = userAccount.password,
            email = userAccount.email,
            role = userAccount.role,
        )
    }

    override fun createFunderProfile(username: String) {
        val funderProfile = FunderProfile(username = username)
        funderProfileRepository.save(funderProfile)
    }

    override fun createResearcherProfile(username: String) {
        val researcherProfile = ResearcherProfile(username = username)
        researcherProfileRepository.save(researcherProfile)
    }

    override fun login(loginRequest: LoginRequest, response: HttpServletResponse): LoginResponse? {
        val userIsExist = findByEmail(loginRequest.email)
        if (!userIsExist) {
            throw BadRequestException("user account not registered")
        }

        val userAccount = getUserByEmail(loginRequest.email)
        val passwordIsSame = comparePassword(loginRequest.password, userAccount?.password)
        if (!passwordIsSame) {
            throw BadRequestException("email and password combination is invalid")
        }

        val jwtToken = jwtConfiguration(userAccount, response)

        return LoginResponse(
            userAccount?.username,
            userAccount?.email,
            userAccount?.role,
            jwtToken
        )
    }

    override fun findByEmail(email: String): Boolean {
        validationUtil.validate(email)
        val user = userAccountRepository.findByEmail(email)
        if (user?.email == null) {
            return false
        }

        return true
    }

    override fun comparePassword(password: String, encodedPassword: String?): Boolean {
        validationUtil.validate(password)
        return BCryptPasswordEncoder().matches(password, encodedPassword)
    }

    override fun getUserByEmail(email: String): UserAccount? {
        validationUtil.validate(email)
        val userAccount = userAccountRepository.findByEmail(email)
        return userAccount
    }

    override fun jwtConfiguration(userAccount: UserAccount?, response: HttpServletResponse): String {
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

        return jwt
    }

    override fun cookieCheck(jwt: String?): Int {
        try {
            if (jwt == null) {
                throw BadRequestException("Akses tidak diizinkan")
            }

            val body = Jwts.parser().setSigningKey("secret-key").parseClaimsJws(jwt).body

            val userAccount = userAccountRepository.findByIdOrNull(body.issuer)
            if (userAccount == null) {
                throw UnauthorizedException("Akses tidak diizinkan")
            }

            if (userAccount?.role == Role.RESEARCHER) {
                val researcherProfile = researcherProfileRepository.findByUsername(userAccount.username)
                return researcherProfile!!.researcherId
            } else {
                val funderProfile = funderProfileRepository.findByUsername(userAccount.username)
                return funderProfile!!.funderId
            }
        } catch (e: Exception) {
            throw BadRequestException("Akses tidak diizinkan")
        }
    }

    override fun deleteCookie(jwt: String?, response: HttpServletResponse) {
        if (jwt == null) {
            throw UnauthorizedException("Akses tidak diizinkan")
        } else {
            val cookie = Cookie("jwt", "")
            cookie.maxAge = 0
            response.addCookie(cookie)
        }
    }
}