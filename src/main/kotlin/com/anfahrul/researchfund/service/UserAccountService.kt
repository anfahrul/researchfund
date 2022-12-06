package com.anfahrul.researchfund.service

import com.anfahrul.researchfund.model.CreateUserAccountRequest
import com.anfahrul.researchfund.model.CreateUserAccountResponse

interface UserAccountService {

    fun create(createUserAccountRequest: CreateUserAccountRequest): CreateUserAccountResponse

}