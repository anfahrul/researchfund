package com.anfahrul.researchfund.entity

import jakarta.persistence.*

@Entity
@Table(name = "proposal")
data class Proposal(

    @Id
    @Column(name = "proposal_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    val proposalId: String = "",

    @Column(name = "researcher_title")
    var researchTitle: String = "",

    @Column(name = "abstrac")
    var abstrac: String = "",

    @Column(name = "keyword")
    var keyword: String = "",

    @Column(name = "file_path")
    var filePath: String = "",

    @Column(name = "research_offer_id")
    val researchOfferId: String = "",

    @Column(name = "researcher_id")
    val researcherId: String = ""
)
