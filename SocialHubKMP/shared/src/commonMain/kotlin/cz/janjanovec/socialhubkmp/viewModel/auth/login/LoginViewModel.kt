package cz.janjanovec.socialhubkmp.viewModel.auth.login

import cz.janjanovec.socialhubkmp.viewModel.BaseViewModel
import cz.janjanovec.socialhubkmp.useCases.auth.LoginUseCase
import org.koin.core.component.inject

open class LoginViewModel: BaseViewModel<LoginContract.Event, LoginContract.State>() {
    private val loginUseCase: LoginUseCase by inject()
    override fun createInitialState(): LoginContract.State =
        LoginContract.State(LoginContract.ILoginState.INITIAL)

    override suspend fun handleEvent(event: LoginContract.Event) {
        when (event) {
            is LoginContract.Event.Login -> login(event.login, event.password)
        }
    }

    private suspend fun login(login: String, password: String) {
        setState { copy(state = LoginContract.ILoginState.LOADING) }

        loginUseCase.invoke(login, password)
            .onSuccess {
                setState { copy(state = LoginContract.ILoginState.SUCCESS) }
            }
            .onFailure {
                setState { copy(state = LoginContract.ILoginState.ERROR(it)) }
            }
    }

}
