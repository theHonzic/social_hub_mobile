package cz.janjanovec.socialhubkmp.viewModel.auth.register

import cz.janjanovec.socialhubkmp.model.selectable.delegates.Country
import cz.janjanovec.socialhubkmp.utils.AlertContainer
import cz.janjanovec.socialhubkmp.utils.FormField
import cz.janjanovec.socialhubkmp.viewModel.UiEvent
import cz.janjanovec.socialhubkmp.viewModel.UiState

interface RegisterContract {
    sealed interface Event : UiEvent {
        object ProceedToTheNextStep : Event
        object Edit : Event
    }

    data class State(
        var page: RegisterPage,
        var alert: AlertContainer,
        // Form
        // Identification
        var email: FormField,
        var phoneNumber: FormField,
        var username: FormField,
        var checkAccountAvailabilityState: CheckAccountAvailabilityState = CheckAccountAvailabilityState.IDLE,
        // Personal details
        var firstName: FormField,
        var lastName: FormField,
        var gender: FormField,
        var country: Country?,
        // TODO: Upload photo
        var registerState: RegisterState = RegisterState.IDLE
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
        ERROR
    }

    enum class RegisterState {
        IDLE,
        LOADING,
        ERROR,
        SUCCESS
    }
}