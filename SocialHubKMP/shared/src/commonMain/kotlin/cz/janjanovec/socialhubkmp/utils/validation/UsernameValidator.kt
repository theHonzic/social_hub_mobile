package cz.janjanovec.socialhubkmp.utils.validation

class UsernameValidator : StringValidator {
    override fun validate(value: String): Boolean {
        return value.length in 7..19 && !value.contains(' ')
    }
}