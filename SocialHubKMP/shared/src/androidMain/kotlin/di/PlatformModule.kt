package di

import dev.icerock.moko.geo.LocationTracker
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.PermissionsControllerImpl
import executor.MainDispatcher
import org.koin.core.module.Module
import org.koin.dsl.module
import persistance.KVaultFactory
import persistance.LocalStorage
import utils.GeoCoder

actual fun platformModule(): Module = module {
    single { KVaultFactory(get()) }
    single { MainDispatcher() }
    single { LocalStorage(get()) }
    single<PermissionsController> { PermissionsControllerImpl(applicationContext = get()) }
    single { LocationTracker(permissionsController = get()) }
    single { GeoCoder(get()) }
}