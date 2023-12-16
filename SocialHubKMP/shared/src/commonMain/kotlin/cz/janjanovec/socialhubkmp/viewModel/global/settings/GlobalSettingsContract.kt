package cz.janjanovec.socialhubkmp.viewModel.global.settings

import cz.janjanovec.socialhubkmp.model.Account
import cz.janjanovec.socialhubkmp.utils.Logger
import cz.janjanovec.socialhubkmp.viewModel.BasicUiState
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
        data class LoggedIn(val account: Account): ApplicationState
        object LoggedOut: ApplicationState
        data class Onboarding(var onboardingStep: Int = 0): ApplicationState
        object Loading: ApplicationState
    }
}