package cz.janjanovec.socialhubkmp.viewModel.auth.login

import cz.janjanovec.socialhubkmp.viewModel.UiEvent
import cz.janjanovec.socialhubkmp.viewModel.UiState

interface LoginContract {
    sealed interface Event : UiEvent {
        data class Login(
            val login: String,
            val password: String,
        ) : Event
    }

    data class State(
        var state: LoginState
    ) : UiState

    enum class LoginState {
        INITIAL,
        SUCCESS,
        ERROR,
        LOADING,
    }
}