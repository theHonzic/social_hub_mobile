package cz.janjanovec.socialhubkmp.api.model.response

import cz.janjanovec.socialhubkmp.model.Account
import cz.janjanovec.socialhubkmp.model.selectable.delegates.Country
import cz.janjanovec.socialhubkmp.model.selectable.delegates.CountryHelper
import cz.janjanovec.socialhubkmp.model.selectable.delegates.Gender
import kotlinx.serialization.Serializable

@Serializable
data class GetMyAccountResponseBody(
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: Int,
    val phoneNumber: String,
    val country: String,
    val profilePicture: String? = null
)

fun GetMyAccountResponseBody.toAccount(): Account =
    Account(
        username,
        firstName,
        lastName,
        Gender.getGenderById(gender) ?: Gender.OTHER,
        email,
        phoneNumber,
        CountryHelper.getCountryByIsoCode(country) ?: CountryHelper.getAll().first(),
    )