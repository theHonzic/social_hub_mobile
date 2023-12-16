package cz.janjanovec.socialhubkmp.useCases.account

import cz.janjanovec.socialhubkmp.api.IHttpClient
import cz.janjanovec.socialhubkmp.model.Account
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import persistance.IKVault
import persistance.KVaultFactory
import persistance.KVaultImpl

class GetMyAccountUseCase(
    private val client: IHttpClient,
    kVaultFactory: KVaultFactory
) {
    private val kVaultStore: IKVault = KVaultImpl(kVaultFactory.generate())
    suspend fun invoke(): Flow<Result<Account>> = flow {
        client.getAccount()
            .onSuccess {
                kVaultStore.saveAccount(it)
                emit(Result.success(it))
            }
            .onFailure {
                kVaultStore.getAccount()?.let { account ->
                    if (account.username.isNotEmpty()) {
                        emit(Result.success(account))
                    } else {
                        emit(Result.failure(it))
                    }
                }
            }
    }
}