package com.anfahrul.researchfund.entity

import jakarta.persistence.*

@Entity
@Table(name = "funder_profile")
data class FunderProfile(
    @Id
    @Column(name = "funder_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    val funderId: Int = 0,

    @Column(name = "organitation_name")
    val organitationName: String = "",

    @Column(name = "address")
    val address: String = "",

    @Column(name = "organitation_description")
    val organitation_description: String = "",

    @Column(name = "phone")
    val phone: String = "",

    @Column(name = "email")
    val email: String = "",

    @Column(name = "logo")
    val logo: String = "",

    @Column(name = "username")
    val username: String
)
