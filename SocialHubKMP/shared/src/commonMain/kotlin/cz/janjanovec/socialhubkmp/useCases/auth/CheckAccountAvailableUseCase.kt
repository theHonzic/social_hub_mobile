package cz.janjanovec.socialhubkmp.useCases.auth

import cz.janjanovec.socialhubkmp.api.IHttpClient
import cz.janjanovec.socialhubkmp.api.model.request.auth.CheckAccountAvailabilityRequestBody

class CheckAccountAvailableUseCase(
    private val client: IHttpClient
) {
    suspend fun invoke(body: CheckAccountAvailabilityRequestBody): Result<Boolean> {
        client.checkAccountAvailability(body)
            .onSuccess {
                return Result.success(it)
            }
            .onFailure {
                return Result.failure(it)
            }
        return Result.failure(Exception("Error checking account availability"))
    }
}