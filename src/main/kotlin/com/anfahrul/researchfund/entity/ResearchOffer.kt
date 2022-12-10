package com.anfahrul.researchfund.entity

import jakarta.persistence.*

@Entity
@Table(name = "research_offer")
data class ResearchOffer(

    @Id
    @Column(name = "research_offer_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    val researchOfferId: String = "",

    @Column(name = "research_offer_name")
    var researchOfferName: String = "",

    @Column(name = "details")
    var details: String = "",

    @Column(name = "category")
    var category: String = "",

    @Column(name = "proposal_funded")
    var proposalFunded: Int = 0,

    @Column(name = "fund")
    var fund: Int = 0,

    @Column(name = "funder_id")
    val funderId: String = ""
)
