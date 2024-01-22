package cz.janjanovec.socialhubkmp.utils

enum class AdditionalAccountData(
    val mandatory: Boolean
) {
    PHONE_NUMBER(true),
    NATIONALITY(false),
    BUSINESS(false),
    PROFILE_PICTURE(true);
}