package com.anfahrul.researchfund.entity

import jakarta.persistence.*

@Entity
@Table(name = "research_experience")
data class ResearchExperience(

    @Id
    @Column(name = "experience_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val experienceId: Int = 0,

    @Column(name = "research_name")
    val researchName: String = "",

    @Column(name = "year")
    val year: Int = 0,

    @Column(name = "source_of_funding")
    val sourceOfFunding: String = "",

    @Column(name = "role_name")
    val roleName: String = "",

    @Column(name = "researcher_id")
    val researcherId: String = ""
)
