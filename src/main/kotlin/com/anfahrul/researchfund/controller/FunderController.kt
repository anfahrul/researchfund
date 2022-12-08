package com.anfahrul.researchfund.controller

import com.anfahrul.researchfund.entity.FunderProfile
import com.anfahrul.researchfund.model.*
import com.anfahrul.researchfund.service.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class FunderController(
    val funderProfileService: FunderProfileService,
    val userAccountService: UserAccountService,
    val middleware: Middleware
) {
    @GetMapping("funder_profile/{funder_id}")
    fun getProfile(
        @PathVariable("funder_id") funderId: String,
        @RequestHeader("Authorization") authorization: String?
    ): WebResponse<Any> {
        userAccountService.authorizationCheck(authorization)

        val getFunderProfileResponse = funderProfileService.get(funderId)

        return WebResponse(
            code = 200,
            status = "Ok",
            data = getFunderProfileResponse
        )
    }

    @PutMapping("funder_profile/update")
    fun updateProfile(
        @RequestBody updateFunderProfile: UpdateFunderProfile,
        @RequestHeader("Authorization") authorization: String?
    ): WebResponse<FunderProfile> {
        val funderId = userAccountService.authorizationCheck(authorization)
        middleware.funderMiddleware(authorization)

        val updateProfileResponse = funderProfileService.update(funderId, updateFunderProfile)

        return WebResponse(
            code = 200,
            status = "Ok",
            data = updateProfileResponse
        )
    }
}