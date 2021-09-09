package com.mohamadrizki.depin.data.model

data class User(
    val userId: String? = null,
    val name: String,
    val email: String,
    val password: String,
    val idNumber: String,
    val phoneNumber: String
)
