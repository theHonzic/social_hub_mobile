package cz.janjanovec.socialhubkmp.viewModel.global.settings

import cz.janjanovec.socialhubkmp.useCases.account.GetMyAccountUseCase
import cz.janjanovec.socialhubkmp.useCases.location.GetUserLocationUseCase
import cz.janjanovec.socialhubkmp.utils.Logger
import cz.janjanovec.socialhubkmp.viewModel.BaseViewModel
import executor.MainDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import persistance.KVaultFactory
import persistance.KVaultImpl
import persistance.LocalStorage

open class GlobalSettingsViewModel: BaseViewModel<GlobalSettingsContract.Event, GlobalSettingsContract.State>() {
    private val getUserLocationUseCase: GetUserLocationUseCase by inject()
    private val getMyAccountUseCase: GetMyAccountUseCase by inject()
    private val factory: KVaultFactory by inject()
    private val kVaultStore = KVaultImpl(factory.generate())
    private val localStore: LocalStorage by inject()
    override fun createInitialState(): GlobalSettingsContract.State =
        GlobalSettingsContract.State(GlobalSettingsContract.ApplicationState.Loading)

    override suspend fun handleEvent(event: GlobalSettingsContract.Event) {
        when (event) {
            is GlobalSettingsContract.Event.FinishOnboarding -> finishOnboarding()
            is GlobalSettingsContract.Event.RefreshAccount -> refreshAccount()
        }
    }

    init {
        launch {
            localStore.isAppNewlyInstalled().let {
                if (it) {
                    kVaultStore.clearAll()
                    setState { copy(appState = GlobalSettingsContract.ApplicationState.Onboarding()) }
                } else {
                    getMyAccountUseCase.invoke().collect { accountResult ->
                        accountResult.onSuccess { account ->
                            setState {
                                copy(
                                    appState = GlobalSettingsContract.ApplicationState.LoggedIn(
                                        account
                                    )
                                )
                            }
                        }
                        accountResult.onFailure {
                            setState { copy(appState = GlobalSettingsContract.ApplicationState.LoggedOut) }
                        }
                    }
                }
            }

            getUserLocationUseCase.invoke().collect {
                it.onSuccess { location ->
                    Logger.log("Location: ${location.coordinates.latitude}, ${location.coordinates.longitude}")
                    Logger.log("Placemark: ${location.placemark}")
                }
                it.onFailure {
                    Logger.log("Location error: $it")
                }

            }
        }
    }

    private fun finishOnboarding() {
        setState { copy(appState = GlobalSettingsContract.ApplicationState.Loading) }
        launch {
            getMyAccountUseCase.invoke().collect { accountResult ->
                accountResult.onSuccess { account ->
                    setState { copy(appState = GlobalSettingsContract.ApplicationState.LoggedIn(account)) }
                }
                accountResult.onFailure {
                    setState { copy(appState = GlobalSettingsContract.ApplicationState.LoggedOut) }
                }
            }
            localStore.setAppNewlyInstalled()
        }
    }

    private fun refreshAccount() {
        setState { copy(appState = GlobalSettingsContract.ApplicationState.Loading) }
        launch {
            getMyAccountUseCase.invoke().collect { accountResult ->
                accountResult.onSuccess { account ->
                    setState { copy(appState = GlobalSettingsContract.ApplicationState.LoggedIn(account)) }
                }
                accountResult.onFailure {
                    setState { copy(appState = GlobalSettingsContract.ApplicationState.LoggedOut) }
                }
            }
        }
    }
}