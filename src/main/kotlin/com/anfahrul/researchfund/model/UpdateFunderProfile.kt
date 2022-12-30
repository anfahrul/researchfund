package com.anfahrul.researchfund.model

import jakarta.persistence.Column
import jakarta.validation.constraints.NotBlank

data class UpdateFunderProfile(
    @field:NotBlank
    var organitationName: String = "",

    @field:NotBlank
    var address: String = "",

    @field:NotBlank
    var organitation_description: String = "",

    @field:NotBlank
    var phone: String = "",

    var logo: String = "",
)
