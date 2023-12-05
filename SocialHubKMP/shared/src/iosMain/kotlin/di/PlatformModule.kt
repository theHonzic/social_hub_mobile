package di

import executor.MainDispatcher
import persistance.KVaultFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual fun platformModule(): Module = module {
    single { KVaultFactory() }
    single { MainDispatcher() }
}