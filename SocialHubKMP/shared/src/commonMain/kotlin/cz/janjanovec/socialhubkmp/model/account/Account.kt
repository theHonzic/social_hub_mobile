package cz.janjanovec.socialhubkmp.model.account

import cz.janjanovec.socialhubkmp.model.selectable.delegates.Country
import cz.janjanovec.socialhubkmp.model.selectable.delegates.Gender
import cz.janjanovec.socialhubkmp.utils.AdditionalAccountData

data class Account(
    val username: String,
    val firstName: String,
    val lastName: String,
    val gender: Gender,
    val email: String,
    val phoneNumber: String?,
    val nationality: Country?,
    val profilePicture: String? = null,
    val businessInfo: BusinessInfo? = null
) {
    val additionalAccountDataRequired: List<AdditionalAccountData>
        get() {
            val list = mutableListOf<AdditionalAccountData>()
            if (phoneNumber.isNullOrEmpty()) {
                list.add(AdditionalAccountData.PHONE_NUMBER)
            }
            if (nationality == null) {
                list.add(AdditionalAccountData.NATIONALITY)
            }
            if (profilePicture.isNullOrEmpty()) {
                list.add(AdditionalAccountData.PROFILE_PICTURE)
            }
            return list.filter { it.mandatory }
        }
}