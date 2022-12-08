package com.anfahrul.researchfund.entity

import jakarta.persistence.*

@Entity
@Table(name = "researchOffer")
data class ResearchOffer(

    @Id
    @Column(name = "research_offer")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val researchOfferId: Int = 0,

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
    val funderId: Int = 0
)
