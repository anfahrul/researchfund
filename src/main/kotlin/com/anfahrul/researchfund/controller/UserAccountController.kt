package com.anfahrul.researchfund.controller

import com.anfahrul.researchfund.entity.Role
import com.anfahrul.researchfund.model.CreateUserAccountRequest
import com.anfahrul.researchfund.model.CreateUserAccountResponse
import com.anfahrul.researchfund.model.WebResponse
import com.anfahrul.researchfund.service.FunderProfileService
import com.anfahrul.researchfund.service.ResearcherProfileService
import com.anfahrul.researchfund.service.UserAccountService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserAccountController(
    val userAccountService: UserAccountService,
    val funderProfileService: FunderProfileService,
    val researcherProfileService: ResearcherProfileService
    ) {
    @PostMapping(
        value = ["api/register"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
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
}