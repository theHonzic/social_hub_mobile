package cz.janjanovec.socialhubkmp.viewModel.auth.register

import cz.janjanovec.socialhubkmp.api.model.request.auth.CheckAccountAvailabilityRequestBody
import cz.janjanovec.socialhubkmp.useCases.auth.CheckAccountAvailableUseCase
import cz.janjanovec.socialhubkmp.useCases.auth.RegisterUseCase
import cz.janjanovec.socialhubkmp.utils.AlertContainer
import cz.janjanovec.socialhubkmp.utils.FormField
import cz.janjanovec.socialhubkmp.utils.validation.FormFieldValidation
import cz.janjanovec.socialhubkmp.viewModel.BaseViewModel
import org.koin.core.component.inject

open class RegisterViewModel: BaseViewModel<RegisterContract.Event, RegisterContract.State>() {
    private val checkAccountAvailableUseCase: CheckAccountAvailableUseCase by inject()
    private val registerUseCase: RegisterUseCase by inject()
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
            ),
            RegisterContract.CheckAccountAvailabilityState.IDLE,
            FormField(
                ""
            ),
            FormField(
                ""
            ),
            FormField(
                ""
            ),
            cz.janjanovec.socialhubkmp.model.selectable.delegates.CountryHelper.getAll().first()
        )

    override suspend fun handleEvent(event: RegisterContract.Event) {
        when(event) {
            is RegisterContract.Event.ProceedToTheNextStep -> proceed()
        }
    }

    val currentPageIsValid: Boolean
        get() = when (currentState.page) {
            RegisterContract.RegisterPage.IDENTIFICATION -> currentState.email.isValid && currentState.phoneNumber.isValid && currentState.username.isValid
            RegisterContract.RegisterPage.PERSONAL_DETAILS -> currentState.firstName.isValid && currentState.lastName.isValid && currentState.gender.isValid && currentState.country != null
            RegisterContract.RegisterPage.UPLOAD_PHOTO -> true
            else -> true
        }

    val buttonText: Boolean
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
                setState {
                    copy(
                        firstName = firstName.copy(triggerValidation = true),
                        lastName = lastName.copy(triggerValidation = true),
                        gender = gender.copy(triggerValidation = true)
                    )
                }
                if (currentPageIsValid) {
                    setState { copy(page = RegisterContract.RegisterPage.UPLOAD_PHOTO) }
                }
            }
            RegisterContract.RegisterPage.UPLOAD_PHOTO -> {
                setState { copy(page = RegisterContract.RegisterPage.CONFIRMATION) }
            }
            RegisterContract.RegisterPage.CONFIRMATION -> {
                setState { copy(registerState = RegisterContract.RegisterState.LOADING) }
                registerUseCase.invoke(
                    currentState.username.value,
                    currentState.firstName.value,
                    currentState.lastName.value,
                    currentState.gender.value,
                    currentState.email.value,
                    currentState.phoneNumber.value,
                    currentState.country?.isoCode ?: "CZ",
                    "password"
                )
                    .onSuccess {
                        if (it) {
                            setState {
                                copy(
                                    registerState = RegisterContract.RegisterState.SUCCESS
                                )
                            }
                        } else {
                            setState {
                                copy(
                                    registerState = RegisterContract.RegisterState.ERROR,
                                    alert = AlertContainer(
                                        "Error",
                                        "Something went wrong",
                                        true
                                    )
                                )
                            }
                        }
                    }
                    .onFailure {
                        setState {
                            copy(
                                registerState = RegisterContract.RegisterState.ERROR
                            )
                        }
                    }
            }
        }
    }
}