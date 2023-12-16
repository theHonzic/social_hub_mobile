package di

import executor.MainDispatcher
import org.koin.core.module.Module
import org.koin.dsl.module
import persistance.KVaultFactory
import persistance.LocalStorage

actual fun platformModule(): Module = module {
    single { KVaultFactory(get()) }
    single { MainDispatcher() }
    single { LocalStorage(get()) }
}