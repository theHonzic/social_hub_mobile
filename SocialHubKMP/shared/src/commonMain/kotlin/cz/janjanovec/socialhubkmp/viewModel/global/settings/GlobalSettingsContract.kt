package cz.janjanovec.socialhubkmp.viewModel.global.settings

import cz.janjanovec.socialhubkmp.model.account.Account
import cz.janjanovec.socialhubkmp.utils.AdditionalAccountData
import cz.janjanovec.socialhubkmp.viewModel.UiEvent
import cz.janjanovec.socialhubkmp.viewModel.UiState

interface GlobalSettingsContract {
    data class State(
        val appState: ApplicationState
    ): UiState
    sealed interface Event: UiEvent {
        object FinishOnboarding: Event
        object RefreshAccount: Event
    }

    sealed interface ApplicationState {
        data class LoggedIn(
            val account: Account,
            val additionalAccountData: List<AdditionalAccountData>
        ): ApplicationState
        object LoggedOut: ApplicationState
        data object Onboarding: ApplicationState
        object Loading: ApplicationState
    }
}