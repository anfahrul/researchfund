package com.anfahrul.researchfund.service.impl

import com.anfahrul.researchfund.entity.UserAccount
import com.anfahrul.researchfund.model.CreateUserAccountRequest
import com.anfahrul.researchfund.model.CreateUserAccountResponse
import com.anfahrul.researchfund.model.LoginRequest
import com.anfahrul.researchfund.model.LoginResponse
import com.anfahrul.researchfund.repository.UserAccountRepository
import com.anfahrul.researchfund.service.UserAccountService
import com.anfahrul.researchfund.validation.ValidationUtil
import jakarta.validation.ConstraintViolationException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserAccountServiceImpl(
    val userAccountRepository: UserAccountRepository,
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

        return CreateUserAccountResponse(
            username = userAccount.username,
            password = userAccount.password,
            email = userAccount.email,
            role = userAccount.role,
        )
    }

    override fun findByEmail(email: String): Boolean {
        val user = userAccountRepository.findByEmail(email)
        if (user?.email == null) {
            return false
        }

        return true
    }

    override fun comparePassword(password: String, encodedPassword: String?): Boolean {
        return BCryptPasswordEncoder().matches(password, encodedPassword)
    }

    override fun getUserByEmail(email: String): UserAccount? {
        val userAccount = userAccountRepository.findByEmail(email)
        return userAccount
    }

}