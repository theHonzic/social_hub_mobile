package di

import executor.MainDispatcher
import persistance.KVaultFactory
import org.koin.core.module.Module
import org.koin.dsl.module
import persistance.LocalStorage

actual fun platformModule(): Module = module {
    single { KVaultFactory() }
    single { MainDispatcher() }
    single { LocalStorage() }
}