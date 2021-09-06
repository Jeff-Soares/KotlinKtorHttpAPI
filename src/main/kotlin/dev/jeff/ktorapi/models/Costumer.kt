package dev.jeff.ktorapi.models

import kotlinx.serialization.Serializable

@Serializable
data class Costumer (
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val fullName: String = "$firstName $lastName"
)