package cz.janjanovec.socialhubkmp.viewModel.auth.register

import cz.janjanovec.socialhubkmp.utils.AlertContainer
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
        var username: FormField
    ) : UiState
    enum class RegisterPage {
        IDENTIFICATION,
        PERSONAL_DETAILS,
        UPLOAD_PHOTO,
        CONFIRMATION
    }
}

data class FormField(
    var value: String,
    val validation: FormFieldValidation,
    var triggerValidation: Boolean = false
) {
    val isValid: Boolean
        get() = if (triggerValidation) {
            validation.validator.validate(value)
        } else {
            true
        }
}

enum class FormFieldValidation(val validator: Validator, val errorMessage: String? = null) {
    EMAIL(EmailValidator(), "Email is not valid"),
    PHONE_NUMBER(PhoneNumberValidator(), "Phone number is not valid"),
    USERNAME(UsernameValidator(), "Username must be between 7 and 19 characters long and can contain only letters and numbers")
}

interface Validator {
    fun validate(value: String): Boolean
}

class EmailValidator : Validator {
    override fun validate(value: String): Boolean {
        return value.contains("@")
    }
}

class PhoneNumberValidator : Validator {
    override fun validate(value: String): Boolean {
        return value.length == 9
    }
}

class UsernameValidator : Validator {
    override fun validate(value: String): Boolean {
        return value.length in 7..19 && value.contains(Regex("[a-zA-Z0-9]"))
    }
}