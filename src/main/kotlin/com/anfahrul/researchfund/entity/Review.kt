package com.anfahrul.researchfund.entity

import jakarta.persistence.*

@Entity
@Table(name = "review")
data class Review(

    @Id
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    val reviewId: String = "",

    @Column(name = "proposal_id")
    var proposalId: String = "",

    @Column(name = "date_reviewed")
    var dateReviewed: String = "",

    @Column(name = "funding_status")
    var fundingStatus: FundingStatus = FundingStatus.NONE,

    @Column(name = "message")
    var message: String = "",
)
