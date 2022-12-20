package com.anfahrul.researchfund.model

data class GetProposalResponse(

    val proposalId: String,

    val researchTitle: String,

    val abstrac: String,

    val keyword: String,

    val filePath: String,

    val researchOfferId: String,

    val researcherId: String
)