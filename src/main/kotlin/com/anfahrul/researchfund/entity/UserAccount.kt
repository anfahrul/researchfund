package com.anfahrul.researchfund.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "user_account")
data class UserAccount(
    @Id
    val username: String,

    @Column(name = "password")
    val password: String,

    @Column(name = "email", unique = true)
    val email: String,

    @Column(name = "role")
    val role: Role
)
