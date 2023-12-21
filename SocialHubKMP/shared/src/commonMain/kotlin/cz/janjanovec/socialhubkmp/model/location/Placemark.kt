package cz.janjanovec.socialhubkmp.model.location

import cz.janjanovec.socialhubkmp.model.selectable.delegates.Country

data class Placemark(
    val street: String? = null,
    val city: String? = null,
    val country: Country? = null,
    val district: String? = null,
    val postalCode: String? = null,
    val placeName: String? = null,
    val administrativeArea: String? = null,
    val subAdministrativeArea: String? = null,
)
