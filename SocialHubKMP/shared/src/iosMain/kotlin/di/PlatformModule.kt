package di

import dev.icerock.moko.geo.LocationTracker
import dev.icerock.moko.permissions.PermissionsController
import executor.MainDispatcher
import persistance.KVaultFactory
import org.koin.core.module.Module
import org.koin.dsl.module
import persistance.LocalStorage
import utils.GeoCoder

actual fun platformModule(): Module = module {
    single { KVaultFactory() }
    single { MainDispatcher() }
    single { LocalStorage() }
    single<PermissionsController> { dev.icerock.moko.permissions.ios.PermissionsController() }
    single { LocationTracker(permissionsController = get()) }
    single { GeoCoder() }
}