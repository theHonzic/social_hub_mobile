package cz.janjanovec.socialhubkmp.model.location

data class UserLocation(
    val coordinates: Coordinates,
    val placemark: Placemark? = null
)