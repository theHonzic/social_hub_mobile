package di

import cz.janjanovec.socialhubkmp.api.HttpClientImpl
import cz.janjanovec.socialhubkmp.api.IHttpClient
import cz.janjanovec.socialhubkmp.useCases.auth.CheckAccountAvailableUseCase
import cz.janjanovec.socialhubkmp.useCases.auth.LoginUseCase
import cz.janjanovec.socialhubkmp.useCases.auth.RegisterUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            platformModule(),
            dispatcherModule,
            client,
            authUseCaseModule
        )
    }

val dispatcherModule = module {
    factory { Dispatchers.Default }
}
fun initKoin() = initKoin {}

// Use cases
// Auth
val authUseCaseModule = module {
    factory { LoginUseCase(get()) }
    factory { RegisterUseCase(get()) }
    factory { CheckAccountAvailableUseCase(get()) }
}

val client: Module = module {
    single<IHttpClient> { HttpClientImpl() }
}