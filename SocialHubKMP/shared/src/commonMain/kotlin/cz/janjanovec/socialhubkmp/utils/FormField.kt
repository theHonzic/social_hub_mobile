package cz.janjanovec.socialhubkmp.utils

import cz.janjanovec.socialhubkmp.utils.validation.FormFieldValidation

data class FormField(
    var value: String,
    val validation: FormFieldValidation? = null,
    var triggerValidation: Boolean = false,
    var isMandatory: Boolean = true
) {
    val isValid: Boolean
        get() = if (triggerValidation) {
            (validation?.validator?.validate(value) ?: true) && (if (isMandatory)  value.isNotEmpty() else true)
        } else {
            true
        }
}