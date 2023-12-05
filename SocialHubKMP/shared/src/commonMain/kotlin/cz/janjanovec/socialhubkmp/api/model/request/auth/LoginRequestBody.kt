package cz.janjanovec.socialhubkmp.api.model.request.auth

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestBody(
    val login: String,
    val password: String
)
