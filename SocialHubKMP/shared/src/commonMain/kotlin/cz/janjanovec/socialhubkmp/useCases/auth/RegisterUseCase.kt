package cz.janjanovec.socialhubkmp.useCases.auth

import cz.janjanovec.socialhubkmp.api.model.request.auth.RegistrationRequestBody
import cz.janjanovec.socialhubkmp.api.IHttpClient

class RegisterUseCase(
    private val client: IHttpClient
) {
    suspend fun invoke(
        username: String,
        firstName: String,
        lastName: String,
        gender: String,
        email: String,
        phoneNumber: String,
        country: String,
        street: String?,
        houseNumber: String?,
        postalCode: String?,
        city: String?,
        password: String
    ): Result<Boolean> {
        client.register(
            RegistrationRequestBody(
            username,
            firstName,
            lastName,
            gender,
            email,
            phoneNumber,
            country,
            street,
            houseNumber,
            postalCode,
            city,
            password
        )
        )
            .onSuccess {
                return Result.success(it)
            }
            .onFailure {
                return Result.failure(it)
            }
        return Result.failure(Exception("Error registering"))
    }
}