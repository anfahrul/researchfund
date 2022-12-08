package com.anfahrul.researchfund.service

import com.anfahrul.researchfund.entity.FunderProfile
import com.anfahrul.researchfund.model.UpdateFunderProfile

interface FunderProfileService {

    fun create(username: String)

    fun get(funderId: String): FunderProfile

    fun update(researcherId: String, updateFunderProfile: UpdateFunderProfile): FunderProfile

}