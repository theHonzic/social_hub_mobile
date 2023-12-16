package cz.janjanovec.socialhubkmp.api.model.response

import kotlinx.serialization.Serializable

@Serializable
data class RegistrationResponseBody(
    val token: String
): ResponseBody