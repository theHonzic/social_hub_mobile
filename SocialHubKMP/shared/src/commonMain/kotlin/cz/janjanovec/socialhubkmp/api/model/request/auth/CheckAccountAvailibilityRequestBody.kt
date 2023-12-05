package cz.janjanovec.socialhubkmp.api.model.request.auth

import cz.janjanovec.socialhubkmp.api.model.request.auth.RequestBody
import kotlinx.serialization.Serializable

@Serializable

data class CheckAccountAvailabilityRequestBody(
    val email: String? = null,
    val username: String? = null,
    val phoneNumber: String? = null
): RequestBody
