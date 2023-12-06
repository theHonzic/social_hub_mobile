package cz.janjanovec.socialhubkmp.utils.validation

class EmailValidator : StringValidator {
    override fun validate(value: String): Boolean {
        return value.matches(Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$"))
    }
}