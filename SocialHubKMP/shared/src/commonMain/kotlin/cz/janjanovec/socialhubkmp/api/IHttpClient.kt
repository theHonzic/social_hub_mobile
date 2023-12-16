package cz.janjanovec.socialhubkmp.api

import cz.janjanovec.socialhubkmp.api.model.request.auth.CheckAccountAvailabilityRequestBody
import cz.janjanovec.socialhubkmp.api.model.request.auth.LoginRequestBody
import cz.janjanovec.socialhubkmp.api.model.request.auth.RegistrationRequestBody
import cz.janjanovec.socialhubkmp.model.Account

interface IHttpClient {
    // Auth
    suspend fun login(body: LoginRequestBody): Result<Boolean>
    suspend fun register(body: RegistrationRequestBody): Result<Boolean>
    suspend fun checkAccountAvailability(body: CheckAccountAvailabilityRequestBody): Result<Boolean>
    // Account
    suspend fun getAccount(): Result<Account>
}