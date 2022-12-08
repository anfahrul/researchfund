package com.anfahrul.researchfund.entity

import jakarta.persistence.*

@Entity
@Table(name = "researcher_profile")
data class ResearcherProfile(
    @Id
    @Column(name = "researcher_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    val researcherId: String = "",

    @Column(name = "name")
    var name: String = "",

    @Column(name = "front_title")
    var frontTitle: String = "",

    @Column(name = "back_degree")
    var backDegree: String = "",

    @Column(name = "photo")
    var photo: String = "",

    @Column(name = "ktp_no")
    var ktpNo: String = "",

    @Column(name = "areas_of_experience")
    var areasOfExperience: String = "",

    @Column(name = "username")
    val username: String
)
