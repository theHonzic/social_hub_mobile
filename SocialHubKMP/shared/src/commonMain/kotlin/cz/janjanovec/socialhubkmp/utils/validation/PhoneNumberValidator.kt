package cz.janjanovec.socialhubkmp.utils.validation

class PhoneNumberValidator : StringValidator {
    override fun validate(value: String): Boolean {
        return value.replace(" ", "").length == 9
    }
}