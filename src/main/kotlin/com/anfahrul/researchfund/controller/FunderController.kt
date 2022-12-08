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
    @PutMapping("funder_profile/{funder_id}/update")
    fun updateProfile(
        @PathVariable("funder_id") funderId: String,
        @RequestBody updateFunderProfile: UpdateFunderProfile,
        @RequestHeader("Authorization") authorization: String?
    ): WebResponse<FunderProfile> {
        userAccountService.authorizationCheck(authorization)
        middleware.funderMiddleware(authorization)

        val updateProfileResponse = funderProfileService.update(funderId, updateFunderProfile)

        return WebResponse(
            code = 200,
            status = "Ok",
            data = updateProfileResponse
        )
    }
}