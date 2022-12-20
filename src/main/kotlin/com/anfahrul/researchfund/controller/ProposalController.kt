package com.anfahrul.researchfund.controller

import com.anfahrul.researchfund.entity.FunderProfile
import com.anfahrul.researchfund.entity.Proposal
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

    @GetMapping("proposal/{proposal_id}")
    fun getProposal(
        @PathVariable("proposal_id") proposalId: String,
        @RequestHeader("Authorization") authorization: String?
    ): WebResponse<GetProposalResponse> {
        userAccountService.authorizationCheck(authorization)

        val proposal = proposalService.get(proposalId)

        return WebResponse(
            code = 200,
            status = "Ok",
            data = proposal
        )
    }

    @PutMapping("proposal/{proposal_id}/update")
    fun updateProposal(
        @PathVariable("proposal_id") proposalId: String,
        @RequestBody proposalRequest: ProposalRequest,
        @RequestHeader("Authorization") authorization: String?
    ): WebResponse<Proposal> {
        userAccountService.authorizationCheck(authorization)
        middleware.researcherMiddleware(authorization)

        val proposal = proposalService.edit(proposalId, proposalRequest)

        return WebResponse(
            code = 200,
            status = "Ok",
            data = proposal
        )
    }

    @GetMapping("{research_offer_id}/proposals")
    fun findProposalByResearchOfferId(
        @PathVariable("research_offer_id") researchOfferId: String,
        @RequestHeader("Authorization") authorization: String?
    ): WebResponse<List<GetProposalResponse>> {
        userAccountService.authorizationCheck(authorization)

        val proposal = proposalService.findByResearchOfferId(researchOfferId)

        return WebResponse(
            code = 200,
            status = "Ok",
            data = proposal
        )
    }

    @PostMapping("proposal/{proposal_id}/review")
    fun review(
        @PathVariable("proposal_id") proposalId: String,
        @RequestHeader("Authorization") authorization: String?,
        @RequestBody reviewRequest: ReviewRequest
    ): WebResponseWithMessage {
        userAccountService.authorizationCheck(authorization)
        middleware.funderMiddleware(authorization)

        val reviewResponse = proposalService.review(proposalId, reviewRequest)

        return WebResponseWithMessage(
            code = 200,
            status = "Ok",
            message = "Review pada proposal ${reviewResponse} telah disimpan"
        )
    }
}