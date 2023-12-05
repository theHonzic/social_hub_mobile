package di

import executor.MainDispatcher
import org.koin.core.module.Module
import org.koin.dsl.module
import persistance.KVaultFactory

actual fun platformModule(): Module = module {
    single { KVaultFactory(get()) }
    single { MainDispatcher() }
}