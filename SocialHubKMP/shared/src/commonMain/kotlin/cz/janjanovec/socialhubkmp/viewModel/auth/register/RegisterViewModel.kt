package cz.janjanovec.socialhubkmp.viewModel.auth.register

import cz.janjanovec.socialhubkmp.utils.AlertContainer
import cz.janjanovec.socialhubkmp.viewModel.BaseViewModel

open class RegisterViewModel: BaseViewModel<RegisterContract.Event, RegisterContract.State>() {
    override suspend fun handleEvent(event: RegisterContract.Event) {
        when(event) {
            is RegisterContract.Event.ProceedToTheNextStep -> proceed()
        }
    }

    val firstPageIsValid: Boolean
        get() = currentState.email.isValid && currentState.phoneNumber.isValid && currentState.username.isValid

    private fun proceed() {

    }

    override fun createInitialState(): RegisterContract.State =
        RegisterContract.State(
            RegisterContract.RegisterPage.IDENTIFICATION,
            AlertContainer(
                "",
                "",
                false
            ),
            FormField(
                "",
                FormFieldValidation.EMAIL
            ),
            FormField(
                "",
                FormFieldValidation.PHONE_NUMBER
            ),
            FormField(
                "",
                FormFieldValidation.USERNAME
            )
        )
}