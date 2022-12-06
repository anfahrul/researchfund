package com.anfahrul.researchfund.entity

import jakarta.persistence.*

@Entity
@Table(name = "researcher_profile")
data class ResearcherProfile(
    @Id
    @Column(name = "researcher_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val researcherId: Int = 0,

    @Column(name = "name")
    val name: String = "",

    @Column(name = "front_title")
    val frontTitle: String = "",

    @Column(name = "back_degree")
    val backDegree: String = "",

    @Column(name = "photo")
    val photo: String = "",

    @Column(name = "ktp_no")
    val ktpNo: String = "",

    @Column(name = "areas_of_experience")
    val areasOfExperience: String = "",

    @Column(name = "username")
    val username: String
)
