package cz.janjanovec.socialhubkmp.viewModel.auth.register

import cz.janjanovec.socialhubkmp.utils.AlertContainer
import cz.janjanovec.socialhubkmp.utils.FormField
import cz.janjanovec.socialhubkmp.viewModel.UiEvent
import cz.janjanovec.socialhubkmp.viewModel.UiState

interface RegisterContract {
    sealed interface Event : UiEvent {
        object ProceedToTheNextStep : Event
    }

    data class State(
        var page: RegisterPage,
        var alert: AlertContainer,
        // Form
        var email: FormField,
        var phoneNumber: FormField,
        var username: FormField,
        var checkAccountAvailabilityState: CheckAccountAvailabilityState = CheckAccountAvailabilityState.IDLE
    ) : UiState
    enum class RegisterPage {
        IDENTIFICATION,
        PERSONAL_DETAILS,
        UPLOAD_PHOTO,
        CONFIRMATION
    }

    enum class CheckAccountAvailabilityState {
        IDLE,
        LOADING,
        AVAILABLE,
        UNAVAILABLE,
        ERROR
    }
}