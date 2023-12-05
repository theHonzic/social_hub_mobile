package cz.janjanovec.socialhubkmp.api.model.request.auth

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationRequestBody(
    val username: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val email: String,
    val phoneNumber: String,
    val country: String,
    val street: String? = null,
    val houseNumber: String? = null,
    val postalCode: String? = null,
    val city: String? = null,
    val password: String
): RequestBody