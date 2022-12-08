package com.anfahrul.researchfund.entity

import jakarta.persistence.*

@Entity
@Table(name = "education")
data class Education(
    @Id
    @Column(name = "education_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val educationId: Int = 0,

    @Column(name = "institution_name")
    val institutionName: String = "",

    @Column(name = "major")
    val major: String = "",

    @Column(name = "graduation_year", length = 20)
    val graduationYear: Int = 0,

    @Column(name = "researcher_id")
    val researcherId: String = ""
)
