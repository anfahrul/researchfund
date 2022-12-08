package com.anfahrul.researchfund.controller

import com.anfahrul.researchfund.entity.ResearcherProfile
import com.anfahrul.researchfund.model.EducationRequest
import com.anfahrul.researchfund.model.ResearchExperienceRequest
import com.anfahrul.researchfund.model.UpdateResearcherProfile
import com.anfahrul.researchfund.model.WebResponse
import com.anfahrul.researchfund.service.EducationService
import com.anfahrul.researchfund.service.ResearchExperienceService
import com.anfahrul.researchfund.service.ResearcherProfileService
import com.anfahrul.researchfund.service.UserAccountService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class ResearcherController(
    val educationService: EducationService,
    val researchExperienceService: ResearchExperienceService,
    val researcherProfileService: ResearcherProfileService,
    val userAccountService: UserAccountService
) {
    @PostMapping("education/add")
    fun addEducation(
        @RequestBody educationRequest: EducationRequest,
        @RequestHeader("Authorization") authorization: String?
    ): WebResponse<Any> {
        val researcherId = userAccountService.cookieCheck(authorization)

        val educationsResponse = educationService.add(researcherId, educationRequest)

        return WebResponse(
            code = 200,
            status = "Add education success",
            data = educationsResponse
        )
    }

    @PostMapping("research_experience/add")
    fun addResearchExperience(@RequestBody researchExperienceRequest: ResearchExperienceRequest, @CookieValue("jwt") jwt: String?): WebResponse<Any> {
        val researcherId = userAccountService.cookieCheck(jwt)

        val researchExperienceResponse = researchExperienceService.add(researcherId, researchExperienceRequest)

        return WebResponse(
            code = 200,
            status = "Add research experience success",
            data = researchExperienceResponse
        )
    }

    @PutMapping("researcher_profile/{researcher_id}/update")
    fun updateProfile(
        @PathVariable("researcher_id") researcherId: Int,
        @RequestBody updateResearcherProfile: UpdateResearcherProfile,
        @CookieValue("jwt") jwt: String?
    ): WebResponse<ResearcherProfile> {
        userAccountService.cookieCheck(jwt)
        val updateProfileResponse = researcherProfileService.update(researcherId, updateResearcherProfile)

        return WebResponse(
            code = 200,
            status = "Ok",
            data = updateProfileResponse
        )
    }

    @GetMapping("education/{researcher_id}")
    fun getEducations(
        @PathVariable("researcher_id") researcherId: Int,
        @RequestHeader("Authorization") authorization: String?
    ): WebResponse<Any> {
        userAccountService.cookieCheck(authorization)

        val getEducationResponse = educationService.getEducationByResearcherId(researcherId)

        return WebResponse(
            code = 200,
            status = "Ok",
            data = getEducationResponse
        )
    }

    @GetMapping("research_experience/{researcher_id}")
    fun getResearchExperience(
        @PathVariable("researcher_id") researcherId: Int,
        @CookieValue("jwt") jwt: String?
    ): WebResponse<Any> {
        userAccountService.cookieCheck(jwt)

        val getResearchExperienceResponse = researchExperienceService.getResearchExperienceByResearcherId(researcherId)

        return WebResponse(
            code = 200,
            status = "Ok",
            data = getResearchExperienceResponse
        )
    }
}