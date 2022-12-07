package com.anfahrul.researchfund.controller

import com.anfahrul.researchfund.entity.FunderProfile
import com.anfahrul.researchfund.entity.ResearcherProfile
import com.anfahrul.researchfund.model.*
import com.anfahrul.researchfund.service.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class FunderController(
    val funderProfileService: FunderProfileService,
    val userAccountService: UserAccountService
) {
    @PutMapping("funder_profile/{funder_id}/update")
    fun updateProfile(
        @PathVariable("funder_id") funderId: Int,
        @RequestBody updateFunderProfile: UpdateFunderProfile,
        @CookieValue("jwt") jwt: String?
    ): WebResponse<FunderProfile> {
        userAccountService.cookieCheck(jwt)
        val updateProfileResponse = funderProfileService.update(funderId, updateFunderProfile)

        return WebResponse(
            code = 200,
            status = "Ok",
            data = updateProfileResponse
        )
    }
}