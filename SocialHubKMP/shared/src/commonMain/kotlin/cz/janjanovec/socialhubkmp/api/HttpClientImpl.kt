package cz.janjanovec.socialhubkmp.api

import persistance.KVaultFactory
import cz.janjanovec.socialhubkmp.api.model.request.auth.LoginRequestBody
import cz.janjanovec.socialhubkmp.api.model.request.auth.RegistrationRequestBody
import cz.janjanovec.socialhubkmp.api.model.response.LoginResponseBody
import cz.janjanovec.socialhubkmp.utils.Logger
import persistance.KVaultImpl
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HttpClientImpl: IHttpClient, KoinComponent {
    private val url = "http://0.0.0.0:8080"
    private val client = HttpClient {
        expectSuccess = true
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })

        }
        install(HttpTimeout) {
            requestTimeoutMillis = 15_000 // 15 seconds
        }
    }

    private val factory: KVaultFactory by inject()
    private val store = KVaultImpl(factory.generate())
    override suspend fun login(body: LoginRequestBody): Result<Boolean> {
        return try {
            val resp = client.post("$url/auth/login") {
                contentType(ContentType.Application.Json)
                setBody(body)
            }
            if (resp.call.response.status == HttpStatusCode.OK) {
                val token = resp.body<LoginResponseBody>().token
                store.saveString("USER_TOKEN", token)
                Result.success(true)
            } else {
                Result.failure(Exception("Error logging in"))
            }
        } catch (e: Exception) {
            Logger.log("Error logging in: ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun register(body: RegistrationRequestBody): Result<Boolean> {
        return try {
            val resp = client.post("$url/auth/register") {
                setBody(body)
            }
            if (resp.call.response.status == HttpStatusCode.OK) {
                Result.success(true)
            } else {
                Result.failure(Exception("Error logging in"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}