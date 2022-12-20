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
class ReviewController(
    val reviewService: ReviewService,
    val userAccountService: UserAccountService,
    val middleware: Middleware
) {

    @GetMapping("proposal/{proposal_id}/reviews")
    fun getReviews(
        @PathVariable("proposal_id") proposalId: String,
        @RequestHeader("Authorization") authorization: String?,
    ): WebResponse<Any> {
        userAccountService.authorizationCheck(authorization)

        val reviewResponses = reviewService.findByProposalId(proposalId)

        return WebResponse(
            code = 200,
            status = "Ok",
            data = reviewResponses
        )
    }

    @GetMapping("reviews/{review_id}")
    fun getReview(
        @PathVariable("review_id") reviewId: String,
        @RequestHeader("Authorization") authorization: String?,
    ): WebResponse<ReviewResponse> {
        userAccountService.authorizationCheck(authorization)

        val reviewResponses = reviewService.get(reviewId)

        return WebResponse(
            code = 200,
            status = "Ok",
            data = reviewResponses
        )
    }
}