package cz.janjanovec.socialhubkmp.model

import cz.janjanovec.socialhubkmp.model.selectable.delegates.Country
import cz.janjanovec.socialhubkmp.model.selectable.delegates.Gender

data class Account(
    val username: String,
    val firstName: String,
    val lastName: String,
    val gender: Gender,
    val email: String,
    val phoneNumber: String,
    val country: Country,
    val profilePicture: String? = null
)
