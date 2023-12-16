package cz.janjanovec.socialhubkmp.api.model.request.auth

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationRequestBody(
    val username: String,
    val firstName: String,
    val lastName: String,
    val gender: Int,
    val email: String,
    val phoneNumber: String,
    val country: String,
    val password: String
): RequestBody