package cz.janjanovec.socialhubkmp.viewModel.auth.register

import cz.janjanovec.socialhubkmp.api.model.request.auth.CheckAccountAvailabilityRequestBody
import cz.janjanovec.socialhubkmp.useCases.auth.CheckAccountAvailableUseCase
import cz.janjanovec.socialhubkmp.utils.AlertContainer
import cz.janjanovec.socialhubkmp.utils.FormField
import cz.janjanovec.socialhubkmp.utils.validation.FormFieldValidation
import cz.janjanovec.socialhubkmp.viewModel.BaseViewModel
import org.koin.core.component.inject

open class RegisterViewModel: BaseViewModel<RegisterContract.Event, RegisterContract.State>() {
    private val checkAccountAvailableUseCase: CheckAccountAvailableUseCase by inject()
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

    override suspend fun handleEvent(event: RegisterContract.Event) {
        when(event) {
            is RegisterContract.Event.ProceedToTheNextStep -> proceed()
        }
    }

    val currentPageIsValid: Boolean
        get() = when (currentState.page) {
            RegisterContract.RegisterPage.IDENTIFICATION -> currentState.email.isValid && currentState.phoneNumber.isValid && currentState.username.isValid
            RegisterContract.RegisterPage.PERSONAL_DETAILS -> true
            RegisterContract.RegisterPage.UPLOAD_PHOTO -> true
            else -> true
        }

    val isProceedButtonLoading: Boolean
        get() = when (currentState.page) {
            RegisterContract.RegisterPage.IDENTIFICATION -> {
                currentState.checkAccountAvailabilityState == RegisterContract.CheckAccountAvailabilityState.LOADING
            }
            RegisterContract.RegisterPage.PERSONAL_DETAILS -> false
            RegisterContract.RegisterPage.UPLOAD_PHOTO -> false
            else -> false
        }

    private suspend fun proceed() {
        when (currentState.page) {
            RegisterContract.RegisterPage.IDENTIFICATION -> {
                setState {
                    copy(
                        email = email.copy(triggerValidation = true),
                        phoneNumber = phoneNumber.copy(triggerValidation = true),
                        username = username.copy(triggerValidation = true)
                    )
                }
                if (currentPageIsValid) {
                    setState { copy(checkAccountAvailabilityState = RegisterContract.CheckAccountAvailabilityState.LOADING) }
                    checkAccountAvailableUseCase.invoke(CheckAccountAvailabilityRequestBody(
                        currentState.email.value,
                        currentState.username.value,
                        currentState.phoneNumber.value

                    ))
                        .onSuccess {
                            if (it) {
                                setState { copy(page = RegisterContract.RegisterPage.PERSONAL_DETAILS) }
                            } else {
                                setState {
                                    copy(
                                        checkAccountAvailabilityState = RegisterContract.CheckAccountAvailabilityState.ERROR,
                                        alert = AlertContainer
                                            (
                                                "Check your details",
                                                "These details might be already assigned to an existing account",
                                                true,
                                                actions = listOf
                                                    (
                                                        Pair("Try again",
                                                            setState {
                                                                copy(
                                                                    checkAccountAvailabilityState = RegisterContract.CheckAccountAvailabilityState.IDLE
                                                                )
                                                            }
                                                        )
                                                )
                                        )
                                    )
                                }
                            }
                        }
                        .onFailure {
                            setState { copy(checkAccountAvailabilityState = RegisterContract.CheckAccountAvailabilityState.ERROR) }
                            setState { copy(alert = AlertContainer("Error", it.message ?: "Something went wrong", true)) }
                        }
                }
            }
            RegisterContract.RegisterPage.PERSONAL_DETAILS -> {
                setState { copy(page = RegisterContract.RegisterPage.UPLOAD_PHOTO) }
            }
            RegisterContract.RegisterPage.UPLOAD_PHOTO -> {
                setState { copy(page = RegisterContract.RegisterPage.CONFIRMATION) }
            }
            else -> {}
        }
    }
}