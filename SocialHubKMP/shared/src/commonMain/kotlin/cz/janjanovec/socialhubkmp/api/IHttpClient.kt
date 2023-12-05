package cz.janjanovec.socialhubkmp.api

import cz.janjanovec.socialhubkmp.api.model.request.auth.LoginRequestBody
import cz.janjanovec.socialhubkmp.api.model.request.auth.RegistrationRequestBody

interface IHttpClient {
    // Auth
    suspend fun login(body: LoginRequestBody): Result<Boolean>
    suspend fun register(body: RegistrationRequestBody): Result<Boolean>

}