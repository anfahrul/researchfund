package com.anfahrul.researchfund.model

import jakarta.persistence.Column
import jakarta.validation.constraints.NotBlank

data class UpdateResearcherProfile(
    @field:NotBlank
    var name: String = "",

    @field:NotBlank
    var frontTitle: String = "",

    @field:NotBlank
    var backDegree: String = "",

    @field:NotBlank
    var photo: String = "",

    @field:NotBlank
    var ktpNo: String = "",

    @field:NotBlank
    var areasOfExperience: String = "",
)
