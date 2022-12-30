package com.anfahrul.researchfund.model

import jakarta.persistence.Column
import jakarta.validation.constraints.NotBlank

data class UpdateResearcherProfile(
    @field:NotBlank
    var name: String = "",

    var frontTitle: String = "",

    var backDegree: String = "",

    var photo: String = "",

    @field:NotBlank
    var ktpNo: String = "",

    @field:NotBlank
    var areasOfExperience: String = "",
)
