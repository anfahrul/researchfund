package com.anfahrul.researchfund.controller

import com.anfahrul.researchfund.entity.FunderProfile
import com.anfahrul.researchfund.entity.ResearchOffer
import com.anfahrul.researchfund.model.*
import com.anfahrul.researchfund.service.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class ResearchOfferController(
    val researchOfferService: ResearchOfferService,
    val userAccountService: UserAccountService
) {
    @PostMapping("research_offer/create")
    fun create(
        @RequestBody researchOfferRequest: ResearchOfferRequest,
        @RequestHeader("Authorization") authorization: String?
    ): WebResponse<ResearchOfferResponse> {
        val funderId = userAccountService.authorizationCheck(authorization)
        val createOfferResponse = researchOfferService.create(funderId, researchOfferRequest)

        return WebResponse(
            code = 200,
            status = "Ok",
            data = createOfferResponse
        )
    }
}