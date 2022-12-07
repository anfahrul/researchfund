package com.anfahrul.researchfund.controller

import com.anfahrul.researchfund.entity.Education
import com.anfahrul.researchfund.exception.BadRequestException
import com.anfahrul.researchfund.model.EducationRequest
import com.anfahrul.researchfund.model.WebResponse
import com.anfahrul.researchfund.service.EducationService
import com.anfahrul.researchfund.service.UserAccountService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api")
class ResearcherController(
    val educationService: EducationService,
    val userAccountService: UserAccountService
) {
    @PostMapping("education/add")
    fun addEducation(@RequestBody educationRequest: EducationRequest, @CookieValue("jwt") jwt: String?): WebResponse<Any> {
        val researcherId = userAccountService.cookieCheck(jwt)

        val educationsResponse = educationService.add(researcherId, educationRequest)

        return WebResponse(
            code = 200,
            status = "Add education success",
            data = educationsResponse
        )
    }
}