package com.anfahrul.researchfund.controller

import com.anfahrul.researchfund.entity.ResearcherProfile
import com.anfahrul.researchfund.model.EducationRequest
import com.anfahrul.researchfund.model.ResearchExperienceRequest
import com.anfahrul.researchfund.model.UpdateResearcherProfile
import com.anfahrul.researchfund.model.WebResponse
import com.anfahrul.researchfund.service.*
import jakarta.websocket.server.PathParam
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class ResearcherController(
    val educationService: EducationService,
    val researchExperienceService: ResearchExperienceService,
    val researcherProfileService: ResearcherProfileService,
    val userAccountService: UserAccountService,
    val middleware: Middleware
) {
    @GetMapping("researcher_profile")
    fun getProfile(
        @RequestHeader("Authorization") authorization: String?
    ): WebResponse<Any> {
        val researcherId = userAccountService.authorizationCheck(authorization)

        val getResearcherProfileResponse = researcherProfileService.get(researcherId)

        return WebResponse(
            code = 200,
            status = "Ok",
            data = getResearcherProfileResponse
        )
    }

    @GetMapping("researcher_profile/{researcher_id}")
    fun getPublicProfile(
        @PathVariable("researcher_id") researcherId: String,
        @RequestHeader("Authorization") authorization: String?
    ): WebResponse<Any> {
        val getResearcherProfileResponse = researcherProfileService.get(researcherId)

        return WebResponse(
            code = 200,
            status = "Ok",
            data = getResearcherProfileResponse
        )
    }

    @PostMapping("education/add")
    fun addEducation(
        @RequestBody educationRequest: EducationRequest,
        @RequestHeader("Authorization") authorization: String?
    ): WebResponse<Any> {
        val researcherId = userAccountService.authorizationCheck(authorization)
        middleware.researcherMiddleware(authorization)

        val educationsResponse = educationService.add(researcherId, educationRequest)

        return WebResponse(
            code = 200,
            status = "Add education success",
            data = educationsResponse
        )
    }

    @PostMapping("research_experience/add")
    fun addResearchExperience(
        @RequestBody researchExperienceRequest: ResearchExperienceRequest,
        @RequestHeader("Authorization") authorization: String?
    ): WebResponse<Any> {
        val researcherId = userAccountService.authorizationCheck(authorization)
        middleware.researcherMiddleware(authorization)

        val researchExperienceResponse = researchExperienceService.add(researcherId, researchExperienceRequest)

        return WebResponse(
            code = 200,
            status = "Add research experience success",
            data = researchExperienceResponse
        )
    }

    @PutMapping("researcher_profile/update")
    fun updateProfile(
        @RequestBody updateResearcherProfile: UpdateResearcherProfile,
        @RequestHeader("Authorization") authorization: String?
    ): WebResponse<ResearcherProfile> {
        val researcherId = userAccountService.authorizationCheck(authorization)
        val updateProfileResponse = researcherProfileService.update(researcherId, updateResearcherProfile)
        middleware.researcherMiddleware(authorization)

        return WebResponse(
            code = 200,
            status = "Ok",
            data = updateProfileResponse
        )
    }

    @GetMapping("education")
    fun getEducations(
        @RequestHeader("Authorization") authorization: String?
    ): WebResponse<Any> {
        val researcherId = userAccountService.authorizationCheck(authorization)

        val getEducationResponse = educationService.getEducationByResearcherId(researcherId)

        return WebResponse(
            code = 200,
            status = "Ok",
            data = getEducationResponse
        )
    }

    @GetMapping("research_experience")
    fun getResearchExperience(
        @RequestHeader("Authorization") authorization: String?
    ): WebResponse<Any> {
        val researcherId = userAccountService.authorizationCheck(authorization)

        val getResearchExperienceResponse = researchExperienceService.getResearchExperienceByResearcherId(researcherId)

        return WebResponse(
            code = 200,
            status = "Ok",
            data = getResearchExperienceResponse
        )
    }
}