package cz.janjanovec.socialhubkmp.useCases.auth

import cz.janjanovec.socialhubkmp.api.model.request.auth.RegistrationRequestBody
import cz.janjanovec.socialhubkmp.api.IHttpClient
import cz.janjanovec.socialhubkmp.model.selectable.delegates.Gender

class RegisterUseCase(
    private val client: IHttpClient
) {
    suspend fun invoke(
        username: String,
        firstName: String,
        lastName: String,
        gender: Gender,
        email: String,
        phoneNumber: String,
        country: String,
        password: String
    ): Result<Boolean> {
        client.register(
            RegistrationRequestBody(
            username,
            firstName,
            lastName,
            gender.genderId,
            email,
            phoneNumber,
            country,
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