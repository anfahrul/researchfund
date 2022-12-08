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
    val userAccountService: UserAccountService,
    val middleware: Middleware
) {
    @PostMapping("research_offer/create")
    fun create(
        @RequestBody researchOfferRequest: ResearchOfferRequest,
        @RequestHeader("Authorization") authorization: String?
    ): WebResponse<ResearchOfferResponse> {
        val funderId = userAccountService.authorizationCheck(authorization)
        middleware.funderMiddleware(authorization)

        val createOfferResponse = researchOfferService.create(funderId, researchOfferRequest)

        return WebResponse(
            code = 200,
            status = "Ok",
            data = createOfferResponse
        )
    }

    @GetMapping("research_offer/{research_offer_id}")
    fun get(
        @PathVariable("research_offer_id") researchOfferId: Int,
        @RequestBody researchOfferRequest: ResearchOfferRequest,
        @RequestHeader("Authorization") authorization: String?
    ): WebResponse<ResearchOffer> {
        userAccountService.authorizationCheck(authorization)
        val getOfferResponse = researchOfferService.get(researchOfferId)

        return WebResponse(
            code = 200,
            status = "Ok",
            data = getOfferResponse
        )
    }

    @GetMapping("funder_profile/{funder_id}/research_offer")
    fun getAllByFunderId(
        @PathVariable("funder_id") funderId: String,
        @RequestHeader("Authorization") authorization: String?
    ): WebResponse<List<ResearchOffer>> {
        userAccountService.authorizationCheck(authorization)
        val getOfferResponseByFunder = researchOfferService.getAllByFunderId(funderId)

        return WebResponse(
            code = 200,
            status = "Ok",
            data = getOfferResponseByFunder
        )
    }

    @GetMapping("research_offers")
    fun getAll(
        @RequestHeader("Authorization") authorization: String?
    ): WebResponse<List<ResearchOffer>> {
        userAccountService.authorizationCheck(authorization)
        val getOfferResponseByFunder = researchOfferService.getAll()

        return WebResponse(
            code = 200,
            status = "Ok",
            data = getOfferResponseByFunder
        )
    }

    @PutMapping("research_offer/{research_offer_id}/update")
    fun update(
        @PathVariable("research_offer_id") researchOfferId: Int,
        @RequestBody researchOfferRequest: ResearchOfferRequest,
        @RequestHeader("Authorization") authorization: String?
    ): WebResponse<ResearchOffer> {
        userAccountService.authorizationCheck(authorization)
        middleware.funderMiddleware(authorization)

        val updateOfferResponse = researchOfferService.update(researchOfferId, researchOfferRequest)

        return WebResponse(
            code = 200,
            status = "Ok",
            data = updateOfferResponse
        )
    }

    @DeleteMapping("research_offer/{research_offer_id}/delete")
    fun delete(
        @PathVariable("research_offer_id") researchOfferId: Int,
        @RequestHeader("Authorization") authorization: String?
    ): WebResponseWithMessage {
        userAccountService.authorizationCheck(authorization)
        middleware.funderMiddleware(authorization)

        researchOfferService.delete(researchOfferId)

        return WebResponseWithMessage(
            code = 200,
            status = "Ok",
            message = "Research offer berhasil dihapus"
        )
    }
}