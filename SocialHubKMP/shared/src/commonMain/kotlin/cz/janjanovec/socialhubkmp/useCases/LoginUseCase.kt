package cz.janjanovec.socialhubkmp.useCases

import cz.janjanovec.socialhubkmp.api.model.request.auth.LoginRequestBody
import cz.janjanovec.socialhubkmp.api.IHttpClient

class LoginUseCase(
    private val client: IHttpClient
) {
    suspend fun invoke(login: String, password: String): Result<Boolean> {
        client.login(LoginRequestBody(login, password))
            .onSuccess {
                return Result.success(it)
            }
            .onFailure {
                return Result.failure(it)
            }
        return Result.failure(Exception("Error logging in"))
    }
}