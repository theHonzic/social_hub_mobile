package cz.janjanovec.socialhubkmp.utils.validation

enum class FormFieldValidation(
    val validator: StringValidator,
    val errorMessage: String? = null
) {
    EMAIL(EmailValidator(), "Email is not valid"),
    PHONE_NUMBER(PhoneNumberValidator(), "Phone number is not valid"),
    USERNAME(UsernameValidator(), "Username must be between 7 and 19 characters long and can contain only letters and numbers")
}