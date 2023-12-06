package cz.janjanovec.socialhubkmp.utils

import cz.janjanovec.socialhubkmp.utils.validation.FormFieldValidation

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