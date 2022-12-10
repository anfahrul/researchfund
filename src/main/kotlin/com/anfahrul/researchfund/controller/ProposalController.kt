package com.anfahrul.researchfund.controller

import com.anfahrul.researchfund.entity.FunderProfile
import com.anfahrul.researchfund.entity.ResearchOffer
import com.anfahrul.researchfund.model.*
import com.anfahrul.researchfund.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("api")
class ProposalController(
    val proposalService: ProposalService,
    val userAccountService: UserAccountService,
    val middleware: Middleware
) {

    @Autowired
    lateinit var proposalStorage: FileStorage

    @PostMapping("proposal/{research_offer_id}/create")
    fun create(
        @PathVariable("research_offer_id") researchOfferId: String,
        @RequestBody proposalRequest: ProposalRequest,
        @RequestHeader("Authorization") authorization: String?
    ): WebResponse<ProposalResponse> {
        val researcherId = userAccountService.authorizationCheck(authorization)
        middleware.researcherMiddleware(authorization)

        val createProposalResponse = proposalService.create(researcherId, researchOfferId, proposalRequest)

        return WebResponse(
            code = 200,
            status = "Ok",
            data = createProposalResponse
        )
    }

    @PostMapping("proposal/{proposal_id}/upload")
    fun uploadProposalFile(
        @PathVariable("proposal_id") proposalId: String,
        @RequestParam("proposal_file") proposalFile: MultipartFile,
        @RequestHeader("Authorization") authorization: String?
    ): WebResponseWithMessage {
        userAccountService.authorizationCheck(authorization)
        middleware.researcherMiddleware(authorization)

        proposalStorage.store(proposalId, proposalFile)
        return WebResponseWithMessage(
            code = 200,
            status = "Ok",
            message = "Upload file success"
        )
    }
}