package com.anfahrul.researchfund.entity

import jakarta.persistence.*

@Entity
@Table(name = "funder_profile")
data class FunderProfile(
    @Id
    @Column(name = "funder_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    val funderId: String = "",

    @Column(name = "organitation_name")
    var organitationName: String = "",

    @Column(name = "address")
    var address: String = "",

    @Column(name = "organitation_description")
    var organitation_description: String = "",

    @Column(name = "phone")
    var phone: String = "",

    @Column(name = "logo")
    var logo: String = "",

    @Column(name = "username")
    val username: String
)
